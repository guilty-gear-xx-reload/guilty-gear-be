package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerConfigRepository extends CrudRepository<PlayerConfigEntity, String> {
  List<PlayerConfigEntity> findAll();

  List<PlayerConfigEntity> findAllByActive(boolean active);

  Optional<PlayerConfigEntity> findByPlayerId(String playerId);
}
