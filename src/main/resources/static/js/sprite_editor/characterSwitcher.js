window.addEventListener('load', bindCharacterChangeListener, false);

function bindCharacterChangeListener(){
      document.getElementById('character').addEventListener('change', loadCharacter, false);
}

function loadCharacter(){
    var character = this.value;
    sendHttpRequest('GET',
            serverDomain + '/characters/postures/' + character + '/total-elements',
            'application/json')
        .then(numberOfPostures => {
            var selectElement = document.getElementById('posture');
            clearOptions(selectElement);
            for (var posture = 0; posture < numberOfPostures; posture++) {
              selectElement.add(new Option(posture));
            }
    })
    loadDefaultPosture();
}

function clearOptions(selectElement) {
    while (selectElement.options.length > 0) {
        selectElement.remove(0);
    }
}

function loadDefaultPosture(){
    var character = document.getElementById('character').value;
    var defaultPosture = 0;
    sendHttpRequest('GET',
            serverDomain + '/palettes/' + character, // display default character
            'application/json')
        .then(responsePalette => {
            actualPalette = JSON.parse(responsePalette);
            drawPalette(actualPalette);
            sendHttpRequest('GET',
                    serverDomain + '/sprites/' + character + '?postureId=' + defaultPosture, // display default sprite with default posture
                    'application/json')
                .then(responseSprite => {
                    actualSprite = JSON.parse(responseSprite);
                    drawSprite(actualSprite, actualPalette);
                })
        })
}