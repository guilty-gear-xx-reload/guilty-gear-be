package ggxnet.reload.player.palette.repository;

import ggxnet.reload.player.palette.entity.CharacterEntity;
import ggxnet.reload.player.palette.entity.PaletteEntity;
import ggxnet.reload.player.palette.entity.PaletteType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaletteRepository extends CrudRepository<PaletteEntity, Long> {

  Optional<PaletteEntity> findByCharacterNameAndPaletteType(
      String characterName, PaletteType paletteType);

  Optional<PaletteEntity> findByCharacterAndPaletteType(
      CharacterEntity character, PaletteType paletteType);

  List<PaletteEntity> findByPaletteType(PaletteType paletteType);

  @Query(
      "Select palette from PaletteEntity palette join CharacterEntity character on palette.character = character.id where character.id not in (?1) and palette.paletteType = 'DEFAULT'")
  List<PaletteEntity> findDefaultPalettesIfCustomNotExist(List<Long> characterIds);
}
