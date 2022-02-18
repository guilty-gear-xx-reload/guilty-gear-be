package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

import static ggxnet.reload.shared.ParamType.*;

@Slf4j
class Enter implements Operation {
    private final PlayerRepository playerRepository;

    public Enter(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public String process(Map<ParamType, String> params) {
        int port = Integer.parseInt(params.get(PORT));
        if (port < 1000 || port > 65535) {
            return "";
        }
        String address = params.get(REMOTE_ADDRESS).concat(":").concat(params.get(PORT));
        Optional<Player> optionalPlayer = playerRepository.findByPortAndAddress(params.get(PORT), params.get(REMOTE_ADDRESS));
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.updateTime();
            log.info("Updated: " + player.getName());
            playerRepository.save(player);
        } else {
            Player newPlayer = new Player(params.get(NAME), params.get(REMOTE_ADDRESS), params.get(NAME), params.get(PORT), params.get(PARAM), params.get(WIN));
            log.info("Added: " + newPlayer.getName());
            playerRepository.save(newPlayer);
        }

        return "##head##" + address + "##foot##\r\n";

    }
}
