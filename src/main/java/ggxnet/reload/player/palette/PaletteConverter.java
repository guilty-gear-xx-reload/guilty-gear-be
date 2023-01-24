package ggxnet.reload.player.palette;

import ggxnet.reload.player.palette.dto.PaletteRGBA;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaletteConverter {

  public static final int PALETTE_SIZE_IN_BYTES = 1040;

  public byte[] convertPaletteFromRgbToBinary(PaletteRGBA paletteRGBA) {
    // convert to rgb
    List<Byte> binaryPalette = new ArrayList<>(PALETTE_SIZE_IN_BYTES);
    for (int i = 0; i < paletteRGBA.rgba().size(); i++) {
      var rgba = paletteRGBA.rgba().get(i);
      binaryPalette.add((byte) rgba.getR());
      binaryPalette.add((byte) rgba.getG());
      binaryPalette.add((byte) rgba.getB());
      binaryPalette.add((byte) rgba.getA());
    }

    // add header
    binaryPalette.addAll(0, paletteRGBA.header());

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
}
