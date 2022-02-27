package ggxnet.reload.lobby.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface PlayerConfigMongoRepository extends CrudRepository<PlayerConfigEntity, String> {
    List<PlayerConfigEntity> findAll();

    List<PlayerConfigEntity> findAllByActive(boolean active);

}
