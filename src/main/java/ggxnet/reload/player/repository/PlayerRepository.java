package ggxnet.reload.player.repository;

import ggxnet.reload.player.entity.PlayerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {
  List<PlayerEntity> findAll();

  Optional<PlayerEntity> findByUserUsername(String username);
}
