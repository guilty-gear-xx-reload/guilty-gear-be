package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.CharacterEntity;
import ggxnet.reload.repository.entity.PaletteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaletteRepository extends CrudRepository<PaletteEntity, Long> {

    Optional<PaletteEntity> findByCharacter(CharacterEntity characterEntity);
}