package ggxnet.reload.service;

import ggxnet.reload.service.dto.PaletteRGBa;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

@Component
public class PaletteConverter {

    public static final int PALETTE_SIZE_IN_BYTES = 1040;

    public static void main(String[] args) throws Exception {

        //PaletteRGBa mPaletteRGBa = readData(rgbPalettePath);
        //convertPaletteFromRgbToBinary(mPaletteRGBa);
    }


    // TODO dodac metode konwertującą z formy binarnej i odwrotnie dla obrazków.bin


    public static byte[] convertPaletteFromRgbToBinary(PaletteRGBa paletteRGBa) throws IOException {
        // convert to rgb
        List<Byte> binaryPalette = new ArrayList<>(PALETTE_SIZE_IN_BYTES);
        for (int i = 0; i < paletteRGBa.rgba().size(); i++) {
            var rgba = paletteRGBa.rgba().get(i);
            binaryPalette.add((byte) rgba.getR());
            binaryPalette.add((byte) rgba.getG());
            binaryPalette.add((byte) rgba.getB());
            binaryPalette.add((byte) rgba.getA());
        }

        // add header
        binaryPalette.addAll(0, paletteRGBa.header());

        // compress
        Deflater deflater = new Deflater();
        deflater.setInput(convertBinaryPaletteToByte(binaryPalette));
        deflater.finish();
        byte[] compressedBinaryPalette = new byte[1000000];
        int deflated = deflater.deflate(compressedBinaryPalette);
        long bytesWritten = deflater.getBytesWritten();
        deflater.end();

        // set zlib header
        byte[] compressedBinaryPaletteWithHeaders =
                addHeadersToCompressedBinaryPalette((int) bytesWritten, compressedBinaryPalette);
        Files.write(Paths.get("deflated.pal"), compressedBinaryPaletteWithHeaders);

        // return
        return compressedBinaryPaletteWithHeaders;
    }

    private static byte[] addHeadersToCompressedBinaryPalette(
            int bytesWritten, byte[] compressedBinaryPalette) {
        byte[] compressedBinaryPaletteWithHeaders = new byte[bytesWritten + 8];
        compressedBinaryPaletteWithHeaders[0] = 0;
        compressedBinaryPaletteWithHeaders[1] = 0;
        compressedBinaryPaletteWithHeaders[2] = 0;
        compressedBinaryPaletteWithHeaders[3] = 0;
        compressedBinaryPaletteWithHeaders[4] = 16;
        compressedBinaryPaletteWithHeaders[5] = 4;
        compressedBinaryPaletteWithHeaders[6] = 0;
        compressedBinaryPaletteWithHeaders[7] = 0;

        int j = 8;
        for (int i = 0; i < bytesWritten; i++) {
            compressedBinaryPaletteWithHeaders[j++] = compressedBinaryPalette[i];
        }
        return compressedBinaryPaletteWithHeaders;
    }

    private static byte[] convertBinaryPaletteToByte(List<Byte> binaryPalette) {
        byte[] binaryPaletteByte = new byte[PALETTE_SIZE_IN_BYTES];
        for (int i = 0; i < binaryPalette.size(); i++) {
            binaryPaletteByte[i] = binaryPalette.get(i);
        }
        return binaryPaletteByte;
    }

    public static PaletteRGBa readData(String filename) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (PaletteRGBa) objectInputStream.readObject();
        }
    }
}
