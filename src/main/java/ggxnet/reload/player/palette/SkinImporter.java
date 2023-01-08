package ggxnet.reload.player.palette;

import ggxnet.reload.player.palette.dto.PaletteRGBA;
import ggxnet.reload.player.palette.entity.CharacterEntity;
import ggxnet.reload.player.palette.entity.PaletteEntity;
import ggxnet.reload.player.palette.entity.PaletteType;
import ggxnet.reload.player.palette.entity.RGBA;
import ggxnet.reload.player.palette.entity.SpriteEntity;
import ggxnet.reload.player.palette.repository.CharacterRepository;
import ggxnet.reload.player.palette.repository.PaletteRepository;
import ggxnet.reload.player.palette.repository.SpriteRepository;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class SkinImporter {
  private static final byte[] mHeader = new byte[16];

  private final CharacterRepository characterRepository;
  private final SpriteRepository spriteRepository;
  private final PaletteRepository paletteRepository;

  public SkinImporter(
      CharacterRepository characterRepository,
      SpriteRepository spriteRepository,
      PaletteRepository paletteRepository) {
    this.characterRepository = characterRepository;
    this.spriteRepository = spriteRepository;
    this.paletteRepository = paletteRepository;
  }

  @Transactional
  public void importAll() throws Exception {
    String palettePath = "importdata/palettes";
    File paletteFolder = new File(palettePath);
    File[] listOfPaletteFiles = paletteFolder.listFiles();
    for (File paletteFile : listOfPaletteFiles) {
      PaletteEntity paletteEntity = new PaletteEntity();

      CharacterEntity characterEntity = new CharacterEntity();
      CharacterEntity savedCharacterEntity = characterRepository.save(characterEntity);
      String characterName = paletteFile.getName().split("_")[0];
      savedCharacterEntity.setName(characterName);
      PaletteRGBA paletteRGBA;
      try {
        paletteRGBA = convertPaletteFromBinaryToRGBA(paletteFile);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      byte[] header = new byte[paletteRGBA.header().size()];
      for (int i = 0; i < paletteRGBA.header().size(); i++) {
        header[i] = paletteRGBA.header().get(i);
      }
      String byteHeader = Base64.getEncoder().encodeToString(header);
      paletteEntity.setHeader(byteHeader);
      paletteEntity.setColors(paletteRGBA.rgba());
      paletteEntity.setFileSizeInBytes(paletteFile.length());
      paletteEntity.setPaletteType(PaletteType.DEFAULT);
      paletteEntity.setCharacter(characterEntity);
      paletteRepository.save(paletteEntity);
      savedCharacterEntity.setPalettes(Collections.singletonList(paletteEntity));
      characterEntity.setPalettes(Collections.singletonList(paletteEntity));

      List<SpriteEntity> spriteEntities = new ArrayList<>();
      String path = "importdata/sprites/" + characterName;
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

  private static PaletteRGBA convertPaletteFromBinaryToRGBA(File paletteFile) throws Exception {
    List<Long> mPalette = readPalette(paletteFile.getPath());
    List<RGBA> paletteReadyToWrite = new ArrayList<>();
    for (int i = 0; i < mPalette.size(); i++) {
      byte[] bytes = longToBytes(mPalette.get(i));
      Color color = getColor(bytes);
      paletteReadyToWrite.add(
          new RGBA(
              (short) color.getRed(),
              (short) color.getGreen(),
              (short) color.getBlue(),
              (short) color.getAlpha()));
    }
    List<Byte> header = new ArrayList<>();
    header.add(mHeader[0]);
    header.add(mHeader[1]);
    header.add(mHeader[2]);
    header.add(mHeader[3]);
    header.add(mHeader[4]);
    header.add(mHeader[5]);
    header.add(mHeader[6]);
    header.add(mHeader[7]);
    header.add(mHeader[8]);
    header.add(mHeader[9]);
    header.add(mHeader[10]);
    header.add(mHeader[11]);
    header.add(mHeader[12]);
    header.add(mHeader[13]);
    header.add(mHeader[14]);
    header.add(mHeader[15]);

    return new PaletteRGBA(header, paletteReadyToWrite);
  }

  private static Color getColor(byte[] bytes) {
    return new Color(
        getColor(bytes[7]), getColor(bytes[6]), getColor(bytes[5]), getColor(bytes[4]));
  }

  private static int getColor(byte number) {
    if (number < 0) {
      return Byte.toUnsignedInt(number);
    }
    return number;
  }

  private static List<Long> readPalette(String filename) throws DataFormatException, IOException {
    Path path = Paths.get(filename);
    byte[] fileContents = Files.readAllBytes(path);
    byte[] newFile = new byte[fileContents.length];
    int j = 0;
    for (int i = 8; i < fileContents.length; i++) {
      newFile[j++] = fileContents[i];
    }
    Inflater inflater = new Inflater();
    inflater.setInput(newFile, 0, newFile.length);
    byte[] buf = new byte[16 + 256 * 4];
    inflater.inflate(buf);
    inflater.end();
    byte[] mPaletteBuf = new byte[1024];
    return getMPalette(buf, mPaletteBuf);
  }

  private static List<Long> getMPalette(byte[] buf, byte[] mPaletteBuf) {
    List<Long> mPalette = new ArrayList<>();
    System.arraycopy(buf, 0, mHeader, 0, 16);
    System.arraycopy(buf, 16, mPaletteBuf, 0, 1024);
    for (int i = 0; i <= mPaletteBuf.length; i++) {
      if (i % 4 == 0 && i > 0) {
        byte[] bytesToConvert = new byte[4];
        bytesToConvert[0] = mPaletteBuf[i - 4];
        bytesToConvert[1] = mPaletteBuf[i - 3];
        bytesToConvert[2] = mPaletteBuf[i - 2];
        bytesToConvert[3] = mPaletteBuf[i - 1];
        mPalette.add(getUInt32(bytesToConvert));
      }
    }
    return mPalette;
  }

  public static long getUInt32(byte[] bytes) {
    return ((bytes[0] & 0xFF))
        | ((bytes[1] & 0xFF) << 8)
        | ((bytes[2] & 0xFF) << 16)
        | ((long) (bytes[3] & 0xFF) << 24);
  }

  public static byte[] longToBytes(long x) {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong(x);
    return buffer.array();
  }
}
