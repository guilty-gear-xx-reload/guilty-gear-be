window.addEventListener('load', clickPaletteAndGetRgbColor, false);

function getElementPosition(element) {
    var rect = element.getBoundingClientRect();
    return {x: rect.left, y: rect.top};
}

function getEventLocation(element, event) {
    var position = getElementPosition(element);
    return {
        x: (event.pageX - position.x),
        y: (event.pageY - position.y)
    };
}

function clickPaletteAndGetRgbColor() {
    var canvas = document.getElementById('palette-canvas');
    canvas.addEventListener("click", function(e) {
        let eventLocation = getEventLocation(this, e);

        let context = this.getContext('2d');
        let pixelData = context.getImageData(eventLocation.x, eventLocation.y, 1, 1).data;

        var r = pixelData[0];
        var g = pixelData[1];
        var b = pixelData[2];

        console.log("r=" + r + ", g=" + g + ", b=" + b);
        document.getElementById("selectedPaletteIndex").innerHTML = calculateClickedPaletteIndex(eventLocation.x, eventLocation.y);
        console.log('x: ' + eventLocation.x, ' - y: ' + eventLocation.y);
        document.getElementById('spriteSelectedPartColor').click();

    });

}

function onInputPaletteColor(that) {
    var colorPickerRgba = hexToRgb(that.value)
    var selectedPaletteIndex = parseInt(document.getElementById('selectedPaletteIndex').innerHTML);
    actualPalette.rgba[selectedPaletteIndex].r = colorPickerRgba.r;
    actualPalette.rgba[selectedPaletteIndex].g = colorPickerRgba.g;
    actualPalette.rgba[selectedPaletteIndex].b = colorPickerRgba.b;
    drawSprite(actualSprite, actualPalette);

    var canvas = document.querySelector('#palette-canvas');
    var context = canvas.getContext('2d');

    context.fillStyle = 'rgb(' + colorPickerRgba.r + ',' + colorPickerRgba.g + ',' + colorPickerRgba.b + ')';
    coordsByIndex = calculateCoordsByPaletteIndex(selectedPaletteIndex);
    context.fillRect(
       delta * coordsByIndex.x,
       delta * coordsByIndex.y,
       delta,
       delta
    );
}

function onClickPaletteColor(that) {
    var selectedPaletteIndex = parseInt(document.getElementById('selectedPaletteIndex').innerHTML);
    var clickedPaletteRgba = actualPalette.rgba[selectedPaletteIndex];
    that.value = rgbToHex(clickedPaletteRgba.r, clickedPaletteRgba.g, clickedPaletteRgba.b)
}

function calculateClickedPaletteIndex(x, y) {
    let roundedUpX = Math.ceil(x / paletteScale);
    let roundedDownY = Math.floor(y / paletteScale);
    return originalPaletteWidth * roundedDownY + roundedUpX - 1;
}

function calculateCoordsByPaletteIndex(index) {
    return {
        x: index % originalPaletteWidth,
        y: Math.trunc(index / originalPaletteWidth)
    }
}

function hexToRgb(hex) {
  var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
  return result ? {
    r: parseInt(result[1], 16),
    g: parseInt(result[2], 16),
    b: parseInt(result[3], 16)
  } : null;
}

function rgbToHex(r, g, b) {
  return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}

function componentToHex(c) {
  var hex = c.toString(16);
  return hex.length == 1 ? "0" + hex : hex;
}