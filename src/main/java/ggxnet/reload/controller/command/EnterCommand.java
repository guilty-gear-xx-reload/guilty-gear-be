package ggxnet.reload.controller.command;

import lombok.Data;

@Data
public class EnterCommand {
  private String playerId;
  private Integer port;
}
