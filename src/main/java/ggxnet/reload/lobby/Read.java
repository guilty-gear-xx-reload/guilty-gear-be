package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
class Read implements Operation {
    private final PlayerRepository playerRepository;

    public Read(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public String process(Map<ParamType, String> params) {
        StringBuilder playersToString = new StringBuilder();
        List<Player> players = playerRepository.findAll();
        playersToString.append("##head##\r\n");
        for (Player player : players) {
            String playerString = player.parseToString();
            playersToString.append(playerString);
        }
        playersToString.append("##foot##\r\n");
        return playersToString.toString();
    }

}
