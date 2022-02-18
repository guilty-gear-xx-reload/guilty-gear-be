package ggxnet.reload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GGXXLobbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GGXXLobbyApplication.class, args);
    }

}
