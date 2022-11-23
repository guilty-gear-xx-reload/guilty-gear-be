package ggxnet.reload.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class PlayerPing {
  private final String playerId;
  private final String ping;
}
