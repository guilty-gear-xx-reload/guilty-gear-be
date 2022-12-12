//TODO nie wiem co z tym zrobić - skąd my mamy to brać? O_o otrząśnij sie
const serverDomain = 'http://26.39.40.108/ggxxreload'

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
              selectElement.add(new Option(characters[i]));
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
            serverDomain + '/palettes/' + defaultCharacter, // display default character
            'application/json')
        .then(responsePalette => {
            palette = JSON.parse(responsePalette);
            drawPalette(palette);
            sendHttpRequest('GET',
                    serverDomain + '/sprites/Sol?postureId=' + defaultPosture, // display default sprite with default posture
                    'application/json')
                .then(responseSprite => {
                    sprite = JSON.parse(responseSprite);
                    drawSprite(sprite, palette);
                })
        })
}