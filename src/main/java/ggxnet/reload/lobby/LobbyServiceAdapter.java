package ggxnet.reload.lobby;

import ggxnet.reload.lobby.port.incoming.LobbyServicePort;
import ggxnet.reload.lobby.port.outgoing.PlayerRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ggxnet.reload.lobby.Parameter.*;

@Slf4j
@RequiredArgsConstructor
class LobbyServiceAdapter implements LobbyServicePort {
    private final PlayerRepositoryPort playerRepositoryPort;

    @Override
    public String processPost(String parsedRequest) {
        log.info("REQUEST {}", parsedRequest);
        var paramService = new ParameterService();
        Map<Parameter, String> params = paramService.calculate(parsedRequest);
        var command = CommandType.valueOf(params.get(Parameter.CMD).toUpperCase());
        log.info("Actual command: {}", command);
        switch (command) {
            case ENTER:
                return handleEnter(params);
            case READ:
                return handleRead();
            case LEAVE:
                return handleLeave(params);
            case REPUP:
                break;
            case REPDOWN:
                break;
            case REPLIST:
                break;
        }
        throw new RuntimeException();
    }

    private String handleRead() {
        StringBuilder playersToString = new StringBuilder();
        List<PlayerData> players = playerRepositoryPort.findAll();
        playersToString.append("##head##\r\n");
        for (PlayerData playerData : players) {
            Player player = Player.of(playerData);
            String playerString = player.parseToString();
            playersToString.append(playerString);
        }
        playersToString.append("##foot##\r\n");
        return playersToString.toString();
    }

    public String handleEnter(Map<Parameter, String> params) {
        int port = Integer.parseInt(params.get(PORT));
        if (port < 1000 || port > 65535) {
            return "";
        }
        String address = params.get(RADMINADDRESS).concat(":").concat(params.get(PORT));
        PlayerData optionalPlayer = playerRepositoryPort.findByName(params.get(NAME));
        if (Objects.nonNull(optionalPlayer)) {
            Player player = Player.of(optionalPlayer);
            player.setStatus(true);
            player.updateTime();
            playerRepositoryPort.save(player);
            log.info("Updated: " + optionalPlayer.getName());
        } else {
            Player newPlayer = new Player(params.get(NAME), params.get(RADMINADDRESS), params.get(PORT), params.get(PARAM), params.get(WIN));
            playerRepositoryPort.save(newPlayer);
            log.info("Added: " + newPlayer.getName());
        }

        return "##head##" + address + "##foot##\r\n";
    }

    public String handleLeave(Map<Parameter, String> params) {
        if (playerRepositoryPort.existsByAddress(params.get(RADMINADDRESS))) {
            log.info("Leaved: " + params.get(NAME));
        }
        var optionalPlayer = playerRepositoryPort.findByName(params.get(NAME));
        if (Objects.nonNull(optionalPlayer)) {
            Player player = Player.of(optionalPlayer);
            player.setStatus(false);
            playerRepositoryPort.save(player);
        }
        return "";
    }

    @Override
    public void processGet(String request) {
    }
}
