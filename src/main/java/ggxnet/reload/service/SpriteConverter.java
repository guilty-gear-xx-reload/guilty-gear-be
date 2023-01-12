package ggxnet.reload.service;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.PaletteRepository;
import ggxnet.reload.repository.SpriteRepository;
import ggxnet.reload.service.dto.PaletteRGBa;
import ggxnet.reload.service.dto.RGBa;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpriteConverter {

  private final PaletteRepository paletteRepository;
  private final SpriteRepository spriteRepository;
  private final CharacterRepository characterRepository;

  public SpriteConverter(
      PaletteRepository paletteRepository,
      SpriteRepository spriteRepository,
      CharacterRepository characterRepository) {
    this.paletteRepository = paletteRepository;
    this.spriteRepository = spriteRepository;
    this.characterRepository = characterRepository;
  }

  public static void main(String[] args) throws Exception {}

  public static List<Short> convertSpriteFromBinaryToRgb(
      String binPath, int spriteWidth, int spriteHeight, List<RGBa> rgbPalette) throws IOException {
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

  public static PaletteRGBa readData(String filename) throws Exception {
    try (FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
      return (PaletteRGBa) objectInputStream.readObject();
    }
  }

  public static <T> void writeDate(T object, String filename) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(object);
    }
  }
}
