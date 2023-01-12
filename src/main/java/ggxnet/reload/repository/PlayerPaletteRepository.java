package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerEntity;
import ggxnet.reload.repository.entity.PlayerPaletteEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PlayerPaletteRepository extends CrudRepository<PlayerPaletteEntity, Long> {

  PlayerPaletteEntity findByPlayer(PlayerEntity player);

  List<PlayerPaletteEntity> findAllByPlayerAndPaletteCharacterName(
      PlayerEntity player, String characterName);

  List<PlayerPaletteEntity> findAllByPlayer(PlayerEntity player);
}
