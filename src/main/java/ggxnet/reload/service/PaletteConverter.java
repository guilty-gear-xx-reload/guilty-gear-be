package ggxnet.reload.service;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.PaletteRepository;
import ggxnet.reload.repository.SpriteRepository;
import ggxnet.reload.repository.entity.CharacterEntity;
import ggxnet.reload.repository.entity.PaletteEntity;
import ggxnet.reload.repository.entity.PaletteType;
import ggxnet.reload.repository.entity.SpriteEntity;
import ggxnet.reload.service.dto.PaletteRGBa;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.zip.Deflater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaletteConverter {

  private final CharacterRepository characterRepository;
  private final PaletteRepository paletteRepository;
  private final SpriteRepository spriteRepository;

  public static final int PALETTE_SIZE_IN_BYTES = 1040;

  public byte[] convertPaletteFromRgbToBinary(PaletteRGBa paletteRGBa) {
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

  public void importAll() throws IOException {
    String palettePath = "default_palettes";
    File paletteFolder = new File(palettePath);
    File[] listOfFilesPalette = paletteFolder.listFiles();
    for (File paletteFile : listOfFilesPalette) {
      PaletteEntity paletteEntity = new PaletteEntity();

      CharacterEntity characterEntity = new CharacterEntity();
      CharacterEntity savedCharacterEntity = characterRepository.save(characterEntity);
      String characterName = paletteFile.getName().split("_")[0];
      savedCharacterEntity.setName(characterName);
      String filename = paletteFile.getName();
      PaletteRGBa paletteRGBa = null;
      try {
        paletteRGBa = readData(palettePath + "\\" + filename);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      byte[] header = new byte[paletteRGBa.header().size()];
      for (int i = 0; i < paletteRGBa.header().size(); i++) {
        header[i] = paletteRGBa.header().get(i);
      }
      String byteHeader = Base64.getEncoder().encodeToString(header);
      paletteEntity.setHeader(byteHeader);
      paletteEntity.setColors(paletteRGBa.rgba());
      paletteEntity.setFileSizeInBytes(paletteFile.length());
      paletteEntity.setPaletteType(PaletteType.DEFAULT);
      paletteEntity.setCharacter(characterEntity);
      paletteRepository.save(paletteEntity);
      savedCharacterEntity.setPalettes(Collections.singletonList(paletteEntity));
      characterEntity.setPalettes(Collections.singletonList(paletteEntity));

      List<SpriteEntity> spriteEntities = new ArrayList<>();
      String path = "scrap\\" + characterName;
      File binFolder = new File(path);
      for (File innerFile : binFolder.listFiles()) {
        String binFile = innerFile.getName();
        int postureId = Integer.parseInt(binFile.split("_")[0].split(".bin")[0]);
        int width = Integer.parseInt(binFile.split("_")[1].split(".bin")[0]);
        int height = Integer.parseInt(binFile.split("_")[2].split(".bin")[0]);

        SpriteEntity spriteEntity = new SpriteEntity();
        spriteEntity.setWidth(width);
        spriteEntity.setHeight(height);
        spriteEntity.setFileSizeInBytes(innerFile.length());
        spriteEntity.setPostureId(postureId);

        byte[] bytes = Files.readAllBytes(Path.of(innerFile.getAbsolutePath()));
        String base64 = Base64.getEncoder().encodeToString(bytes);
        spriteEntity.setColorIndexes(base64);
        spriteEntities.add(spriteEntity);
        spriteEntity.setCharacter(characterEntity);
      }
      spriteRepository.saveAll(spriteEntities);
      savedCharacterEntity.setSprites(spriteEntities);
    }
  }
}
