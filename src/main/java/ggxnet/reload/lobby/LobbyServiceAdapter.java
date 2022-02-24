package ggxnet.reload.lobby;

import ggxnet.reload.client.command.GameCommand;
import ggxnet.reload.lobby.port.incoming.LobbyServicePort;
import ggxnet.reload.lobby.port.outgoing.PlayerRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
class LobbyServiceAdapter implements LobbyServicePort {
    private final PlayerRepositoryPort playerRepositoryPort;

    @Override
    public String processPost(Parameters testCommand) {
        log.info("REQUEST {}", testCommand);
        var paramService = new ParameterService();
        //  Map<Parameter, String> params = paramService.calculate(gameCommand);
        var command = CommandType.valueOf(testCommand.getCmd().toUpperCase());
        log.info("Actual command: {}", command);

        throw new RuntimeException();
    }

    @Override
    public String read(GameCommand command) {
        StringBuilder playersToString = new StringBuilder();
        List<PlayerData> players = playerRepositoryPort.findAll();
        //playersToString.append("##head##\r\n");
        for (PlayerData playerData : players) {
            Player player = Player.of(playerData);
            String playerString = player.parseToString();
            playersToString.append(playerString);
        }
       // playersToString.append("##foot##\r\n");
        return playersToString.toString();
    }

    @Override
    public String enter(GameCommand command) {
        //   return "##head##26.68.204.99:10000##foot##";
        return "26.68.204.99:10000";
/*        int port = command.getPort();
        if (port < 1000 || port > 65535) {
            return "";
        }
        String address = command.getRadminAddress().concat(":").concat(String.valueOf(command.getPort()));
        PlayerData optionalPlayer = playerRepositoryPort.findByName(command.getName());
        if (Objects.nonNull(optionalPlayer)) {
            Player player = Player.of(optionalPlayer);
            player.setStatus(true);
            player.updateTime();
            playerRepositoryPort.save(player);
            log.info("Updated: " + optionalPlayer.getName());
        } else {
            Player newPlayer = new Player(command.getName(), command.getRadminAddress(), String.valueOf(command.getPort()), command.getParam(), String.valueOf(command.getWin()));
            playerRepositoryPort.save(newPlayer);
            log.info("Added: " + newPlayer.getName());
        }

        return "##head##" + address + "##foot##\r\n";*/
    }


    @Override
    public String leave(GameCommand command) {
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
    public void processGet(String request) {
    }
}
