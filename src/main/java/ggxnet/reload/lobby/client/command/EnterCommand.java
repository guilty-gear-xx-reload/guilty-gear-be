package ggxnet.reload.lobby.client.command;

import lombok.Data;

@Data
public class EnterCommand {
    private String playerId;
    private Integer port;
}
