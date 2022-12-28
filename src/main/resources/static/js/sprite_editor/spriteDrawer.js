function drawSprite(sprite, palette) {
    var startTime = performance.now();
    const imgWidth = sprite.width;
    const imgHeight = sprite.height;
    document.getElementById('sprite-canvas').width = imgWidth;
    document.getElementById('sprite-canvas').height = imgHeight;

    var canvas = document.querySelector('#sprite-canvas');
    var context = canvas.getContext('2d');

    // to increase performance createImageData method
    // should be executed once e.g. before drawing
    var image = context.createImageData(canvas.width, canvas.height);
    var data = image.data;

    // draw sprite
    var x = 0;
    var y = 0;
    sprite.spriteColorIndexes.forEach(indexToPalette => {
        if (y == imgHeight) {
            return;
        }
        drawPixel(x, y, indexToPalette);
        x++;
        if (x == imgWidth) {
            x = 0;
            y++;
        }
    });
    swapBuffer();

    var endTime = performance.now();
    console.log(`Drawing took ${endTime - startTime} milliseconds`);

    function drawPixel(x, y, indexToPalette) {
        var roundedX = Math.round(x);
        var roundedY = Math.round(y);

        var index = 4 * (canvas.width * roundedY + roundedX);

        data[index + 0] = palette.rgba[indexToPalette].r;
        data[index + 1] = palette.rgba[indexToPalette].g;
        data[index + 2] = palette.rgba[indexToPalette].b;
        data[index + 3] = 255;
    }

    function swapBuffer() {
        context.putImageData(image, 0, 0);
    }
}

