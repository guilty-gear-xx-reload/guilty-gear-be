package ggxnet.reload.lobby.domain.port.incoming;

import ggxnet.reload.lobby.client.command.PlayerIdCommand;
import ggxnet.reload.lobby.client.command.PlayerConfigCommand;

public interface PlayerConfigServicePort {

    String read(PlayerIdCommand command);

    String enter(PlayerIdCommand command);

    String leave(PlayerIdCommand command);

    void createConfig(PlayerConfigCommand command);

    String getPlayerConfig(String playerId);
}
