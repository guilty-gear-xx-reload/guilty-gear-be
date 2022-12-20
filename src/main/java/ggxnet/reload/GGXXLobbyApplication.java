package ggxnet.reload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableScheduling
@EnableWebSocketMessageBroker
public class GGXXLobbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GGXXLobbyApplication.class, args);
    }
}
