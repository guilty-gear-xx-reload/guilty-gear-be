package ggxnet.reload.lobby.domain;

import ggxnet.reload.lobby.client.command.EnterCommand;
import ggxnet.reload.lobby.client.command.PlayerConfigCommand;
import ggxnet.reload.lobby.client.command.PlayerIdCommand;
import ggxnet.reload.lobby.domain.port.incoming.PlayerConfigServicePort;
import ggxnet.reload.lobby.domain.port.outgoing.PlayerConfigRepositoryPort;
import ggxnet.reload.lobby.domain.projection.PlayerConfigData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
class PlayerConfigServiceAdapter implements PlayerConfigServicePort {
    private final PlayerConfigRepositoryPort playerConfigRepositoryPort;

    @Override
    public String read(PlayerIdCommand command) {
        StringBuilder playersToString = new StringBuilder();
        List<PlayerConfigData> playersConfig = playerConfigRepositoryPort.findAll()
                .stream()
                .filter(playerConfigData -> !playerConfigData.getId().equals(command.getPlayerId()))
                .collect(Collectors.toList());
        for (PlayerConfigData playerConfigData : playersConfig) {
            PlayerConfig playerConfig = PlayerConfig.of(playerConfigData);
            playersToString.append(playerConfig.parseToString());
        }
        return playersToString.toString();
    }

    @Override
    public String enter(EnterCommand command) {
        PlayerConfigData playerConfigData = playerConfigRepositoryPort.findByPlayerId(command.getPlayerId());
        PlayerConfig playerConfig = PlayerConfig.of(playerConfigData);
        playerConfig.activatePlayer(command.getPort());
        playerConfigRepositoryPort.save(playerConfig);
        log.info("Updated: " + playerConfig.getUserName());
        return playerConfig.getScriptAddress().concat(":").concat(String.valueOf(playerConfig.getPort()));
    }


    @Override
    public String leave(PlayerIdCommand command) {
/*        if (playerRepositoryPort.existsByAddress(command.getRadminAddress())) {
            log.info("Leaved: " + command.getName());
        }
        var optionalPlayer = playerRepositoryPort.findByName(command.getName());
        if (Objects.nonNull(optionalPlayer)) {
            Player player = Player.of(optionalPlayer);
            player.setStatus(false);
            playerRepositoryPort.save(player);
        }*/
        return "";
    }

    @Override
    public void createConfig(PlayerConfigCommand command) {
        PlayerStats playerStats = PlayerStats.builder()
                .id(UUID.randomUUID().toString())
                .build();
        PlayerConfig playerConfig = PlayerConfig.of(command);
        playerConfig.setId(UUID.randomUUID().toString());
        playerConfig.setPlayerStats(playerStats);
        playerConfigRepositoryPort.create(playerConfig);
    }

    @Override
    public String getPlayerConfig(String playerId) {
        PlayerConfigData playerConfigData = playerConfigRepositoryPort.findByPlayerId(playerId);
        PlayerConfig playerConfig = PlayerConfig.of(playerConfigData);
        return playerConfig.parseToConfigString();
    }


}
