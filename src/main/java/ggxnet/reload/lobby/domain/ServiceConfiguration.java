package ggxnet.reload.lobby.domain;

import ggxnet.reload.lobby.domain.port.incoming.PlayerConfigServicePort;
import ggxnet.reload.lobby.domain.port.incoming.PlayerStatsServicePort;
import ggxnet.reload.lobby.domain.port.outgoing.PlayerConfigRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class ServiceConfiguration {
    private final PlayerConfigRepositoryPort playerConfigRepositoryPort;

    @Bean
    public PlayerConfigServicePort getLobbyServiceAdapter() {
        return new PlayerConfigServiceAdapter(playerConfigRepositoryPort);
    }

    @Bean
    public PlayerStatsServicePort getPlayerStatsServicePort() {
        return new PlayerStatsServiceAdapter(playerConfigRepositoryPort);
    }
}
