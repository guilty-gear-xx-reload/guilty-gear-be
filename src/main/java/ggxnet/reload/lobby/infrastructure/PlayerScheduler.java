package ggxnet.reload.lobby.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@Slf4j
@RequiredArgsConstructor
class PlayerScheduler {

    private final PlayerConfigMongoRepository playerConfigMongoRepository;

    @Scheduled(fixedDelay = 30, timeUnit = SECONDS)
    public void deactivateNonActivePlayers() {
        playerConfigMongoRepository.findAllByActive(true)
                .stream()
                .filter(player -> player.getLastActivity() + 300 < Instant.now().getEpochSecond())
                .forEach(playerEntity -> {
                    playerEntity.deactivate();
                    playerConfigMongoRepository.save(playerEntity);
                    log.info("{} was deactivated", playerEntity);
                });
    }

}
