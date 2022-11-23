package ggxnet.reload.repository;

import static java.util.concurrent.TimeUnit.SECONDS;

import ggxnet.reload.service.PingService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class PingScheduler {
  private final PingService pingService;
  private final SimpMessagingTemplate messagingTemplate;
  private final PlayerConfigMongoRepository playerConfigMongoRepository;

  @Scheduled(fixedDelayString = "${schedule.ping-players.frequency}", timeUnit = SECONDS)
  public void pingPlayers() {
    var playersPing =
        playerConfigMongoRepository.findAllByActive(true).stream()
            .map(pingService::sendPingRequest)
            .collect(Collectors.toList());
    messagingTemplate.convertAndSend("/topic/pings", playersPing);
  }
}
