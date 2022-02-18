package ggxnet.reload.lobby;

import ggxnet.reload.shared.CommandType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
class OperationConfig {
    private final PlayerRepository playerRepository;

    @Bean
    public Map<CommandType, Operation> getOperations() {
        return Map.of(CommandType.ENTER, new Enter(playerRepository),
                CommandType.LEAVE, new Leave(playerRepository),
                CommandType.READ, new Read(playerRepository),
                CommandType.REPDOWN, new Repdown(),
                CommandType.REPLIST, new Replist(),
                CommandType.REPUP, new Repup());
    }
}
