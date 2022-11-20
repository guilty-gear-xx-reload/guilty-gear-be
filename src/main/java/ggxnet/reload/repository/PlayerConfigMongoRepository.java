package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerConfigMongoRepository extends CrudRepository<PlayerConfigEntity, String> {
  List<PlayerConfigEntity> findAll();

  List<PlayerConfigEntity> findAllByActive(boolean active);
}
