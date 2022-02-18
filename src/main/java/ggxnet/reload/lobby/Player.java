package ggxnet.reload.lobby;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Getter
@Setter
@NoArgsConstructor
class Player {
    @Id
    public String id;
    public String name;
    public String address;
    public String port;
    public String param;
    public String win;
    public long time;

    public Player(String id, String name, String address, String port, String param, String win) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.port = port;
        this.param = param;
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
