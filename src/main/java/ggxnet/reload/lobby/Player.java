package ggxnet.reload.lobby;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
@AllArgsConstructor
class Player implements PlayerData {
    private String id;
    private String name;
    private String address;
    private String port;
    private String param;
    private boolean status;
    private String win;
    private long time;

    public Player(String name, String address, String port, String param, String win) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.param = param;
        this.status = true;
        this.win = win;
        this.time = Instant.now().getEpochSecond();
    }

    public static Player of(PlayerData optionalPlayer) {
        return new Player(optionalPlayer.getId(),
                optionalPlayer.getName(),
                optionalPlayer.getAddress(),
                optionalPlayer.getPort(),
                optionalPlayer.getParam(),
                optionalPlayer.isStatus(),
                optionalPlayer.getWin(),
                optionalPlayer.getTime()
        );
    }

    public void updateTime() {
        this.time = Instant.now().getEpochSecond();
    }


    public String parseToString() {
        return name.concat("@")
                .concat(address)
                .concat(":")
                .concat(port)
                .concat("%")
                .concat(param)
                .concat("#")
                .concat(win)
                .concat("\r\n");
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
