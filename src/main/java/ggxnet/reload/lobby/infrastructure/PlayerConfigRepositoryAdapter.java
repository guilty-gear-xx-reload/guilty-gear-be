package ggxnet.reload.lobby.infrastructure;

import ggxnet.reload.lobby.domain.projection.PlayerConfigData;
import ggxnet.reload.lobby.domain.port.outgoing.PlayerConfigRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
class PlayerConfigRepositoryAdapter implements PlayerConfigRepositoryPort {
    private final PlayerConfigMongoRepository playerConfigMongoRepository;

    @Override
    public List<PlayerConfigData> findAll() {
        List<PlayerConfigEntity> playerEntities = playerConfigMongoRepository.findAll();
        return new ArrayList<>(playerEntities);
    }

    @Override
    public PlayerConfigData findByPlayerId(String playerId) {
        return playerConfigMongoRepository.findById(playerId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(PlayerConfigData playerConfig) {
        PlayerConfigEntity playerConfigEntity = PlayerConfigEntity.of(playerConfig);
        playerConfigMongoRepository.save(playerConfigEntity);
    }

    @Override
    public void create(PlayerConfigData playerConfig) {
        PlayerConfigEntity playerConfigEntity = PlayerConfigEntity.of(playerConfig);
        playerConfigMongoRepository.save(playerConfigEntity);
    }
}
