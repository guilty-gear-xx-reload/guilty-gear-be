package ggxnet.reload.lobby;

import static java.util.concurrent.TimeUnit.SECONDS;

import ggxnet.reload.player.config.PlayerConfigRepository;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class PingScheduler {
  private final PingService pingService;
  private final SimpMessagingTemplate messagingTemplate;
  private final PlayerConfigRepository playerConfigRepository;

  PingScheduler(
      PingService pingService,
      SimpMessagingTemplate messagingTemplate,
      PlayerConfigRepository playerConfigRepository) {
    this.pingService = pingService;
    this.messagingTemplate = messagingTemplate;
    this.playerConfigRepository = playerConfigRepository;
  }

  @Scheduled(fixedDelayString = "${schedule.ping-players.frequency}", timeUnit = SECONDS)
  public void pingPlayers() {
    var playersPing =
        playerConfigRepository.findAllByActive(true).stream()
            .map(pingService::sendPingRequest)
            .collect(Collectors.toList());
    messagingTemplate.convertAndSend("/topic/pings", playersPing);
  }
}
