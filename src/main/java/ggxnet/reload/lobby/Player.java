package ggxnet.reload.lobby;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
class Player {
    @Id
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
}
