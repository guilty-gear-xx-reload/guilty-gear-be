package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerEntity;
import ggxnet.reload.repository.entity.PlayerPaletteEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerPaletteRepository extends CrudRepository<PlayerPaletteEntity, Long> {

    PlayerPaletteEntity findByPlayer(PlayerEntity player);

}