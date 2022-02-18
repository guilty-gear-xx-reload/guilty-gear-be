package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

import static ggxnet.reload.shared.ParamType.*;

@Slf4j
class Leave implements Operation {

    private final PlayerRepository playerRepository;

    public Leave(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public String process(Map<ParamType, String> params) {
        if (playerRepository.existsByAddress(params.get(REMOTE_ADDRESS))) {
            log.info("Leaved: " + params.get(NAME));
        }
        var optionalPlayer = playerRepository.findByName(params.get(NAME));
        if(Objects.nonNull(optionalPlayer)) {
            optionalPlayer.setStatus(false);
            playerRepository.save(optionalPlayer);
        }
        return "";
    }
}
