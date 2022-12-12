package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.CharacterEntity;
import ggxnet.reload.repository.entity.SpriteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpriteRepository extends CrudRepository<SpriteEntity, Long> {

    Optional<SpriteEntity> findByCharacterAndPostureId(CharacterEntity characterEntity, int postureId);

    int countByCharacter(CharacterEntity characterEntity);
}