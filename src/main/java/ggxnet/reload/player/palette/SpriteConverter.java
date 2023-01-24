package ggxnet.reload.player.palette;

import ggxnet.reload.player.palette.dto.PaletteRGBA;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpriteConverter {

  public static List<Short> convertSpriteFromBinaryToRgb(
      String binPath, int spriteWidth, int spriteHeight) throws IOException {
    List<Short> rgbSprite = new ArrayList<>();
    byte[] binarySprite = Files.readAllBytes(Paths.get(binPath));

    var x = 0;
    var y = 0;
    for (byte element : binarySprite) {
      if (y == spriteHeight) {
        break;
      }
      int pixel = Byte.toUnsignedInt(element);
      rgbSprite.add((short) pixel);
      x++;
      if (x == spriteWidth) {
        x = 0;
        y++;
      }
    }
    return rgbSprite;
  }

  public static PaletteRGBA readData(String filename) throws Exception {
    try (FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
      return (PaletteRGBA) objectInputStream.readObject();
    }
  }

  public static <T> void writeDate(T object, String filename) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(object);
    }
  }
}
