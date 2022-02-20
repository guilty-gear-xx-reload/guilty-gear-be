package ggxnet.reload.infrastructure;

import ggxnet.reload.lobby.PlayerData;
import ggxnet.reload.lobby.port.outgoing.PlayerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
class PlayerRepositoryAdapter implements PlayerRepositoryPort {
    private final PlayerMongoRepository playerMongoRepository;

    @Override
    public PlayerData findByName(String name) {
        return playerMongoRepository.findByName(name);
    }

    @Override
    public boolean existsByAddress(String address) {
        return playerMongoRepository.existsByAddress(address);
    }

    @Override
    public List<PlayerData> findAll() {
        List<PlayerEntity> playerEntities = playerMongoRepository.findAll();
        return new ArrayList<>(playerEntities);
    }

    @Override
    public void save(PlayerData playerData) {
        PlayerEntity playerEntity = PlayerEntity.of(playerData);
        playerMongoRepository.save(playerEntity);
    }
}
