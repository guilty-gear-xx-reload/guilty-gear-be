package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerStatsEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatRepository extends CrudRepository<PlayerStatsEntity, String> {

  Optional<PlayerStatsEntity> findByPlayerId(String playerId);
}
