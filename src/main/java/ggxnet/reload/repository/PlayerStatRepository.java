package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerStatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerStatRepository extends CrudRepository<PlayerStatsEntity, String> {

  Optional<PlayerStatsEntity> findByPlayerId(String playerId);
}
