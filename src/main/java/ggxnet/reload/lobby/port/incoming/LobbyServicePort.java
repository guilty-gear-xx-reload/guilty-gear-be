package ggxnet.reload.lobby.port.incoming;

public interface LobbyServicePort {
    String processPost(String parsedRequest);

    void processGet(String request);
}
