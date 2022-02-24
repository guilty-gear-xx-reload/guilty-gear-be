package ggxnet.reload.lobby.port.incoming;

import ggxnet.reload.client.command.GameCommand;
import ggxnet.reload.lobby.Parameters;

public interface LobbyServicePort {
    String processPost(Parameters command);

    String read(GameCommand command);

    String enter(GameCommand command);

    String leave(GameCommand command);

    void processGet(String request);
}
