package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerEntity;
import ggxnet.reload.repository.entity.PlayerPaletteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerPaletteRepository extends CrudRepository<PlayerPaletteEntity, Long> {

    PlayerPaletteEntity findByPlayer(PlayerEntity player);

    List<PlayerPaletteEntity> findAllByPlayerAndPaletteCharacterName(PlayerEntity player, String characterName);
}