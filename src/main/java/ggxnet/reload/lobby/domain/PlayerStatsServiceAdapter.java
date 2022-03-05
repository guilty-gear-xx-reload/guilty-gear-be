package ggxnet.reload.lobby.domain;

import ggxnet.reload.lobby.domain.port.incoming.PlayerStatsServicePort;
import ggxnet.reload.lobby.domain.port.outgoing.PlayerConfigRepositoryPort;
import ggxnet.reload.lobby.domain.projection.PlayerConfigData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PlayerStatsServiceAdapter implements PlayerStatsServicePort {
    private final PlayerConfigRepositoryPort playerConfigRepositoryPort;

    @Override
    public void addWin(String playerId) {
        PlayerConfigData playerConfigData = playerConfigRepositoryPort.findByPlayerId(playerId);
        PlayerConfig playerConfig = PlayerConfig.of(playerConfigData);
        playerConfig.getStats().win();
        playerConfigRepositoryPort.save(playerConfig);
    }

    @Override
    public void addLose(String playerId) {
        PlayerConfigData playerConfigData = playerConfigRepositoryPort.findByPlayerId(playerId);
        PlayerConfig playerConfig = PlayerConfig.of(playerConfigData);
        playerConfig.getStats().lose();
        playerConfigRepositoryPort.save(playerConfig);
    }

    @Override
    public void addDraw(String playerId) {
        PlayerConfigData playerConfigData = playerConfigRepositoryPort.findByPlayerId(playerId);
        PlayerConfig playerConfig = PlayerConfig.of(playerConfigData);
        playerConfig.getStats().draw();
        playerConfigRepositoryPort.save(playerConfig);
    }
}
