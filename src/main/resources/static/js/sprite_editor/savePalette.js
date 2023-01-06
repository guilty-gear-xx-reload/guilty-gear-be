
function onClickSavePalette() {
    var character = document.getElementById('character').value;
    var name = document.getElementById('paletteName').value;
    sendHttpRequest('POST',
            serverDomain + '/palettes/' + character, // display default character
            'application/json',
            {'rgba': actualPalette.rgba, 'paletteName': name})
        .then(responsePalette => {
            console.log('save operation')
        })
}