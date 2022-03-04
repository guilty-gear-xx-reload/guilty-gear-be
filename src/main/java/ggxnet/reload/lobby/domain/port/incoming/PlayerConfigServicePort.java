package ggxnet.reload.lobby.domain.port.incoming;

import ggxnet.reload.lobby.client.command.EnterCommand;
import ggxnet.reload.lobby.client.command.PlayerConfigCommand;
import ggxnet.reload.lobby.client.command.PlayerIdCommand;

public interface PlayerConfigServicePort {

    String read(PlayerIdCommand command);

    String enter(EnterCommand command);

    void leave(PlayerIdCommand command);

    void createConfig(PlayerConfigCommand command);

    String getPlayerConfig(String playerId);
}
