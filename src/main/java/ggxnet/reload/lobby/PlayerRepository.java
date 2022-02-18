package ggxnet.reload.lobby;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByPortAndAddress(String port, String address);
}
