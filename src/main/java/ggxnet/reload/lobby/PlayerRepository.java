package ggxnet.reload.lobby;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface PlayerRepository extends MongoRepository<Player, String> {
    Player findByName(String name);
    List<Player> findAllByStatus(boolean status);

    boolean existsByAddress(String address);
}
