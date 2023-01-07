function onClickSavePalette() {
    var character = document.getElementById('character').value;
    var paletteName = document.getElementById('inputSavePaletteName').value;
    sendHttpRequest('POST',
            serverDomain + '/palettes/custom/' + character, // display default character
            'application/json',
            {'rgba': actualPalette.rgba, 'paletteName': paletteName})
        .then(responsePalette => {
            console.log('save operation')
        })
}