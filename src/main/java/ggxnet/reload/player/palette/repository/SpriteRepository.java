package ggxnet.reload.player.palette.repository;

import ggxnet.reload.player.palette.entity.CharacterEntity;
import ggxnet.reload.player.palette.entity.SpriteEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpriteRepository extends CrudRepository<SpriteEntity, Long> {

  Optional<SpriteEntity> findByCharacterAndPostureId(
      CharacterEntity characterEntity, int postureId);

  int countByCharacter(CharacterEntity characterEntity);
}
