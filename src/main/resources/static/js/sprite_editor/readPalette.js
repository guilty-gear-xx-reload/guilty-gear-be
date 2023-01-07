function getPlayerPalettesForCharacter() {
    var character = document.getElementById('character').value;
    sendHttpRequest('GET',
            serverDomain + '/palettes/custom/characters/' + character,
            'application/json')
            .then(responseData => {
                var playerPaletteNames = JSON.parse(responseData);
                var selectElement = document.getElementById('playerCustomPalettes');
                clearOptions(selectElement);
                Object.entries(playerPaletteNames).map(([id, inputSavePaletteName]) => {
                    selectElement.add(new Option(inputSavePaletteName, id));
                })
            })
}

function getPlayerPaletteById(paletteId) {
    sendHttpRequest('GET',
            serverDomain + '/palettes/custom/' + paletteId,
            'application/json')
            .then(responseData => {
                actualPalette = JSON.parse(responseData);
                drawPalette(actualPalette);
                drawSprite(actualSprite, actualPalette);
            })
}

function onChangePlayerCustomPalettes(that) {
    var selectedPaletteId = that.options[that.selectedIndex].value;
    getPlayerPaletteById(selectedPaletteId);
}