package ggxnet.reload.configuration;

import ggxnet.reload.service.operation.*;
import ggxnet.reload.shared.CommandType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OperationConfig {
    @Bean
    public Map<CommandType, Operation> getOperations() {
        return Map.of(CommandType.ENTER, new Enter(),
                CommandType.LEAVE, new Leave(),
                CommandType.READ, new Read(),
                CommandType.REPDOWN, new Repdown(),
                CommandType.REPLIST, new Replist(),
                CommandType.REPUP, new Repup());
    }
}
