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
        context.fillStyle = "blue";
        context.fillRect(
           delta * Math.trunc(eventLocation.x / 10),
           delta * Math.trunc(eventLocation.y / 10),
           delta,
           delta
        );


    })
}