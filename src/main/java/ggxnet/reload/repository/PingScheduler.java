package ggxnet.reload.repository;

import ggxnet.reload.service.PingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PingScheduler {
  private final PingService pingService;
  private final SimpMessagingTemplate messagingTemplate;
  private final PlayerConfigMongoRepository playerConfigMongoRepository;

  @Scheduled(fixedDelay = 2_000)
  public void getPingsOfThePlayers() {
    var playersPing =
        playerConfigMongoRepository.findAllByActive(false).stream()
            .map(pingService::sendPingRequest)
            .toList();
    messagingTemplate.convertAndSend("/topic/pings", playersPing);
  }
  // TODO  ==>  Trzeba ogarnąć czemu isReachable nie działa dla tych adresów radminowych
  // TODO  ==>  Trzeba zaktualizować elementy na froncie. Można pomyśleć o jakimś id
}
