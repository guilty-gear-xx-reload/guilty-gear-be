package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.CharacterEntity;
import ggxnet.reload.repository.entity.PaletteEntity;
import ggxnet.reload.repository.entity.PaletteType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaletteRepository extends CrudRepository<PaletteEntity, Long> {

    Optional<PaletteEntity> findByCharacterNameAndPaletteType(String characterName, PaletteType paletteType);
    Optional<PaletteEntity> findByCharacterAndPaletteType(CharacterEntity character, PaletteType paletteType);

}