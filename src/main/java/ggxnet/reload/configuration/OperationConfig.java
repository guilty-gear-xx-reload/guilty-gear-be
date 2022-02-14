package ggxnet.reload.configuration;

import ggxnet.reload.service.operation.*;
import ggxnet.reload.shared.CommandType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OperationConfig {
    @Value("${config.file}")
    private String configFile;
    @Bean
    public Map<CommandType, Operation> getOperations() {
        return Map.of(CommandType.ENTER, new Enter(configFile),
                CommandType.LEAVE, new Leave(configFile),
                CommandType.READ, new Read(configFile),
                CommandType.REPDOWN, new Repdown(),
                CommandType.REPLIST, new Replist(),
                CommandType.REPUP, new Repup());
    }
}
