package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerConfigRepository extends CrudRepository<PlayerConfigEntity, String> {
  List<PlayerConfigEntity> findAll();

  List<PlayerConfigEntity> findAllByActive(boolean active);

  Optional<PlayerConfigEntity> findById(String playerId);
}
