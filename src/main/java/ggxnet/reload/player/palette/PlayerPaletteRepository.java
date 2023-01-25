package ggxnet.reload.player.palette;

import ggxnet.reload.player.entity.PlayerEntity;
import ggxnet.reload.player.palette.entity.PlayerPaletteEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PlayerPaletteRepository extends CrudRepository<PlayerPaletteEntity, Long> {

  PlayerPaletteEntity findByPlayer(PlayerEntity player);

  List<PlayerPaletteEntity> findAllByPlayerAndPaletteCharacterName(
      PlayerEntity player, String characterName);

  List<PlayerPaletteEntity> findAllByPlayer(PlayerEntity player);

  void deleteByPaletteId(Long id);
}
