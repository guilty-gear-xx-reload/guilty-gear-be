package ggxnet.reload.lobby.client.menu;

import ggxnet.reload.lobby.infrastructure.PlayerConfigMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LobbyService {

    private final PlayerConfigMongoRepository playerConfigMongoRepository;

    public LobbyService(PlayerConfigMongoRepository playerConfigMongoRepository) {
        this.playerConfigMongoRepository = playerConfigMongoRepository;
    }


    public List<PlayerData> getPlayersData() {
        return playerConfigMongoRepository.findAll().stream()
                .map(LobbyMapper::toPlayerData).collect(Collectors.toList());
    }
}
