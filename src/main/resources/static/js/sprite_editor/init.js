//TODO nie wiem co z tym zrobić - skąd my mamy to brać? O_o otrząśnij sie
const serverDomain = 'http://localhost/ggxxreload'

const sendHttpRequest = (method, url, contentType, body) => {
    return fetch(url, {
            method: method,
            body: JSON.stringify(body),
            headers: {
                'Content-Type': contentType
            }
        }).then(response => {
            return response.text();
        })
        .catch(err => {
            console.log(err, err.data)
        })
}

window.addEventListener('load', (e) => {
    initPostures();
    initCharacters();
    loadDefaultCharacterWithDefaultPosture();
})

function initCharacters() {
    sendHttpRequest('GET',
            serverDomain + '/characters/names',
            'application/json')
        .then(responseCharacters => {
            var characters = JSON.parse(responseCharacters);
            var selectElement = document.getElementById('character');
            for (var i = 0; i < characters.length; i++) {
              var characterName = characters[i];
              var option = new Option(characterName);
              if(characterName == 'Sol') { // choose default character on drop-down html tag
                option.selected = true;
              }
              selectElement.add(option);
            }
    })
}

function initPostures() {
    sendHttpRequest('GET',
            serverDomain + '/characters/postures/Sol/total-elements',
            'application/json')
        .then(numberOfPostures => {
            var selectElement = document.getElementById('posture');
            for (var posture = 0; posture < numberOfPostures; posture++) {
              selectElement.add(new Option(posture));
            }
    })
}

function loadDefaultCharacterWithDefaultPosture(){
    var defaultCharacter = 'Sol';
    var defaultPosture = 0;
    sendHttpRequest('GET',
            serverDomain + '/palettes/default/' + defaultCharacter, // display default character
            'application/json')
        .then(responsePalette => {
            actualPalette = JSON.parse(responsePalette);
            drawPalette(actualPalette);
            sendHttpRequest('GET',
                    serverDomain + '/sprites/Sol?postureId=' + defaultPosture, // display default sprite with default posture
                    'application/json')
                .then(responseSprite => {
                    actualSprite = JSON.parse(responseSprite);
                    drawSprite(actualSprite, actualPalette);
                })
        })
}
