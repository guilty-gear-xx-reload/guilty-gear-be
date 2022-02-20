package ggxnet.reload.infrastructure;

import ggxnet.reload.lobby.PlayerData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@AllArgsConstructor
class PlayerEntity implements PlayerData {
    @Id
    private String id;
    private String name;
    private String address;
    private String port;
    private String param;
    private boolean status;
    private String win;
    private long time;

    public static PlayerEntity of(PlayerData playerData) {
        return new PlayerEntity(playerData.getId(),
                playerData.getName(),
                playerData.getAddress(),
                playerData.getPort(),
                playerData.getParam(),
                playerData.isStatus(),
                playerData.getWin(),
                playerData.getTime()
        );
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
