package ggxnet.reload.lobby;

import ggxnet.reload.lobby.port.outgoing.PlayerRepositoryPort;
import ggxnet.reload.lobby.port.incoming.LobbyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class Config {
    private final PlayerRepositoryPort playerRepositoryPort;

    @Bean
    public LobbyServicePort getLobbyServiceAdapter() {
        return new LobbyServiceAdapter(playerRepositoryPort);
    }
}
