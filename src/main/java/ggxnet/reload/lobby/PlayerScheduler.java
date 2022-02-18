package ggxnet.reload.lobby;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@Slf4j
@RequiredArgsConstructor
public class PlayerScheduler {

    private final PlayerRepository playerRepository;

    @Scheduled(fixedDelay = 30, timeUnit = SECONDS)
    public void deactivateNonActivePlayers() {
        playerRepository.findAllByStatus(true)
                .stream()
                .filter(player -> player.getTime() + 300 < Instant.now().getEpochSecond())
                .forEach(p -> {
                    p.setStatus(false);
                    playerRepository.save(p);
                    log.info("{} was deactivated", p);
                });
    }

}
