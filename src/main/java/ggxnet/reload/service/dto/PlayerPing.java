package ggxnet.reload.service.dto;

public final class PlayerPing {
  private final String playerId;
  private final String ping;

  public PlayerPing(String playerId, String ping) {
    this.playerId = playerId;
    this.ping = ping;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getPing() {
    return ping;
  }
}
