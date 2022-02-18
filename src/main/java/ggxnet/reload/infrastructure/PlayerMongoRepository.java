package ggxnet.reload.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface PlayerMongoRepository extends MongoRepository<PlayerEntity, String> {
    PlayerEntity findByName(String name);

    List<PlayerEntity> findAllByStatus(boolean status);

    boolean existsByAddress(String address);
}
