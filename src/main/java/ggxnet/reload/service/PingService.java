package ggxnet.reload.service;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import ggxnet.reload.service.dto.PlayerPing;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PingService {

  @Value("${schedule.ping-players.timeout}")
  private Integer timeout;

  public PlayerPing sendPingRequest(PlayerConfigEntity playerConfig) {
    String ipAddress = playerConfig.getScriptAddress();
    String ping;
    long start = System.currentTimeMillis();
    long finish;

    InetAddress inetAddress = getInetAddressByName("http://" + ipAddress);
    log.info("Pinging " + ipAddress);
    if (isAddressReachable(inetAddress)) {
      finish = System.currentTimeMillis();
      ping = String.valueOf(finish - start).concat(" ms");
      log.info("Reply from {}: time={}", inetAddress, ping);
      return new PlayerPing(playerConfig.getId(), ping);
    } else {
      log.info("Reply from {}: Destination host unreachable", inetAddress);
      throw new RuntimeException();
    }
  }

  private boolean isAddressReachable(InetAddress inetAddress) {
    try {
      return inetAddress.isReachable(timeout);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private InetAddress getInetAddressByName(String ipAddress) {
    try {
      String hostname = new URL(ipAddress).getHost();
      return InetAddress.getByName(hostname);
    } catch (UnknownHostException e) {
      log.debug("The host with given address={} is unknown", ipAddress);
      throw new RuntimeException();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
