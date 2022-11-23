package ggxnet.reload.repository;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class PlayerScheduler {
  private final PlayerConfigMongoRepository playerConfigMongoRepository;

  @Value("${schedule.afk-players.delay}")
  private Integer delayTime;

  @Scheduled(fixedDelayString = "${schedule.afk-players.frequency}", timeUnit = SECONDS)
  public void deactivatePlayers() {
    playerConfigMongoRepository.findAllByActive(true).stream()
        .filter(player -> player.getLastActivity() + delayTime < Instant.now().getEpochSecond())
        .forEach(
            playerEntity -> {
              playerEntity.deactivate();
              playerConfigMongoRepository.save(playerEntity);
              log.info("{} has been deactivated", playerEntity);
            });
  }
}
