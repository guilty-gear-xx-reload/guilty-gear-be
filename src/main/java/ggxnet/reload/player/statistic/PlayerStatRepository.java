package ggxnet.reload.player.statistic;

import ggxnet.reload.player.statistic.entity.PlayerStatsEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatRepository extends CrudRepository<PlayerStatsEntity, String> {

  Optional<PlayerStatsEntity> findByPlayerId(String playerId);
}
