package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {
  List<PlayerEntity> findAll();

}
