window.addEventListener('load', bindPostureChangeListener, false);

function bindPostureChangeListener(){
      document.getElementById('posture').addEventListener('change', loadPosture, false);
}

function loadPosture(){
    var character = document.getElementById('character').value;
    var posture = this.value;
    sendHttpRequest('GET',
            serverDomain + '/palettes/default/' + character,
            'application/json')
        .then(responsePalette => {
            drawPalette(actualPalette);
            sendHttpRequest('GET',
                    serverDomain + '/sprites/' + character + '?postureId=' + posture,
                    'application/json')
                .then(responseSprite => {
                    actualSprite = JSON.parse(responseSprite);
                    drawSprite(actualSprite, actualPalette);
                })
        })
}

