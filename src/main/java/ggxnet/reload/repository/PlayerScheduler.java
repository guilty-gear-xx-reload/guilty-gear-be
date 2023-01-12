package ggxnet.reload.repository;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class PlayerScheduler {
  private final PlayerConfigRepository playerConfigRepository;

  @Value("${schedule.afk-players.delay}")
  private Integer delayTime;

  PlayerScheduler(PlayerConfigRepository playerConfigRepository) {
    this.playerConfigRepository = playerConfigRepository;
  }

  @Scheduled(fixedDelayString = "${schedule.afk-players.frequency}", timeUnit = SECONDS)
  public void deactivatePlayers() {
    playerConfigRepository.findAllByActive(true).stream()
        .filter(player -> player.getLastActivity() + delayTime < Instant.now().getEpochSecond())
        .forEach(
            playerEntity -> {
              playerEntity.deactivate();
              playerConfigRepository.save(playerEntity);
              log.info("{} has been deactivated", playerEntity);
            });
  }
}
