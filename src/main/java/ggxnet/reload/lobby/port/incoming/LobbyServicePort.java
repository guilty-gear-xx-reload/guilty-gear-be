package ggxnet.reload.lobby.port.incoming;

public interface LobbyServicePort {
    String processPost(String parsedRequest, String remoteAddress);

    void processGet(String request);
}
