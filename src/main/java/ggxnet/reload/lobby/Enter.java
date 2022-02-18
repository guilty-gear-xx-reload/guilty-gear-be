package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

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
        Player optionalPlayer = playerRepository.findByName(params.get(NAME));
        if (Objects.nonNull(optionalPlayer)) {
            optionalPlayer.setStatus(true);
            optionalPlayer.updateTime();
            playerRepository.save(optionalPlayer);
            log.info("Updated: " + optionalPlayer.getName());
        } else {
            Player newPlayer = new Player(params.get(NAME), params.get(REMOTE_ADDRESS), params.get(PORT), params.get(PARAM), params.get(WIN));
            playerRepository.save(newPlayer);
            log.info("Added: " + newPlayer.getName());
        }

        return "##head##" + address + "##foot##\r\n";

    }
}
