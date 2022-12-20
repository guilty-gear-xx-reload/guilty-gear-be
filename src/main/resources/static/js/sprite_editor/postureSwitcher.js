window.addEventListener('load', bindPostureChangeListener, false);

function bindPostureChangeListener(){
      document.getElementById('posture').addEventListener('change', loadPosture, false);
}

function loadPosture(){
    var character = document.getElementById('character').value;
    var posture = this.value;
    sendHttpRequest('GET',
            serverDomain + '/palettes/' + character, // display default character
            'application/json')
        .then(responsePalette => {
            palette = JSON.parse(responsePalette);
            drawPalette(palette);
            sendHttpRequest('GET',
                    serverDomain + '/sprites/' + character + '?postureId=' + posture, // display default sprite with default posture
                    'application/json')
                .then(responseSprite => {
                    sprite = JSON.parse(responseSprite);
                    drawSprite(sprite, palette);
                })
        })
}