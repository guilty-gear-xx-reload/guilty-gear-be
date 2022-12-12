const paletteScale = 9; // increase this to change size of the palette
const originalPaletteWidth = 8;
const originalPaletteHeight = 32;
const paletteWidth = originalPaletteWidth * paletteScale;
const paletteHeight = originalPaletteHeight * paletteScale;
const delta = paletteWidth / originalPaletteWidth;

function drawPalette(palette) {
    document.getElementById('palette-canvas').width = paletteWidth;
    document.getElementById('palette-canvas').height = paletteHeight;

    var canvas = document.querySelector('#palette-canvas');
    var context = canvas.getContext('2d');

    var index = 0;
    for (let i = 0; i < paletteHeight / paletteScale; i++) {
       for (let j = 0; j < paletteWidth / paletteScale; j++) {
          context.fillStyle = getCssRgbaFromPaletteByIndex(index++);
          context.fillRect(
             delta * j,
             delta * i,
             delta,
             delta
          );
       }
    }

    function getCssRgbaFromPaletteByIndex(index) {
        paletteRgba = palette.rgba[index];
        return 'rgba(' + paletteRgba.r + ', ' + paletteRgba.g + ', ' + paletteRgba.b + ', 1.0)';
    }
}