package ggxnet.reload.lobby.domain.port.outgoing;

import ggxnet.reload.lobby.domain.projection.PlayerConfigData;

import java.util.List;

public interface PlayerConfigRepositoryPort {
    List<PlayerConfigData> findAll();

    PlayerConfigData findByPlayerId(String playerId);

    void save(PlayerConfigData playerConfig);

    void create(PlayerConfigData playerConfig);
}
