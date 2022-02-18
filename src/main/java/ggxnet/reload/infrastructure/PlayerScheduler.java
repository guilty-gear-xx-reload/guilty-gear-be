package ggxnet.reload.infrastructure;

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

    private final PlayerMongoRepository playerMongoRepository;

    @Scheduled(fixedDelay = 30, timeUnit = SECONDS)
    public void deactivateNonActivePlayers() {
        playerMongoRepository.findAllByStatus(true)
                .stream()
                .filter(player -> player.getTime() + 300 < Instant.now().getEpochSecond())
                .forEach(playerEntity -> {
                    playerEntity.setStatus(false);
                    playerMongoRepository.save(playerEntity);
                    log.info("{} was deactivated", playerEntity);
                });
    }

}
