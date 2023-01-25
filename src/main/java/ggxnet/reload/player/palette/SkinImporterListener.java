package ggxnet.reload.player.palette;

import ggxnet.reload.player.palette.entity.PaletteType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SkinImporterListener {

  private static final long TOTAL_NUMBER_OF_DEFAULT_SPRITES = 13409;
  private static final long TOTAL_NUMBER_OF_DEFAULT_PALETTES = 23;
  private static final long TOTAL_NUMBER_OF_CHARACTERS = 23;

  private final SkinImporter skinImporter;
  private final SpriteRepository spriteRepository;
  private final PaletteRepository paletteRepository;
  private final CharacterRepository characterRepository;

  public SkinImporterListener(
      SkinImporter skinImporter,
      SpriteRepository spriteRepository,
      PaletteRepository paletteRepository,
      CharacterRepository characterRepository) {
    this.skinImporter = skinImporter;
    this.spriteRepository = spriteRepository;
    this.paletteRepository = paletteRepository;
    this.characterRepository = characterRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void checker() {
    try {
      long totalSprites = spriteRepository.count();
      long totalPalettes = paletteRepository.countByPaletteType(PaletteType.DEFAULT);
      long totalCharacters = characterRepository.count();

      if (totalSprites != TOTAL_NUMBER_OF_DEFAULT_SPRITES
          && totalPalettes != TOTAL_NUMBER_OF_DEFAULT_PALETTES
          && totalCharacters != TOTAL_NUMBER_OF_CHARACTERS) {
        skinImporter.importAll();
        log.info("Sprites have been imported!");
        log.info("Palettes have been imported!");
        log.info("Characters have been imported!");
      } else {
        log.info("Sprites are already imported!");
        log.info("Palettes are already imported!");
        log.info("Characters are already imported!");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
