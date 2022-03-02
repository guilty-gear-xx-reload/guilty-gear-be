package ggxnet.reload.lobby.infrastructure;

import ggxnet.reload.lobby.domain.projection.PlayerConfigData;
import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class PlayerConfigEntity implements PlayerConfigData {
    @Id
    private String id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinProperty(name = "stats")
    private PlayerStatsEntity stats;
    private int version;
    private String scriptAddress;
    private String userName;
    private String trip;
    private Integer port;
    private Integer delay;
    private Integer wait;
    private Integer dispInvCombo;
    private Integer slowRate;
    private Integer rounds;
    private String message;
    private long lastActivity;
    private boolean enableNet;
    private boolean ignoreMissNode;
    private boolean ignoreSlow;
    private boolean useEx;
    private boolean fpsEnable;
    private boolean broadcastEnable;
    private boolean intrusionEnable;
    private boolean watchReplayEnable;
    private boolean watchMaxNodesEnable;
    private boolean active;

    static PlayerConfigEntity of(PlayerConfigData playerConfigData) {
        return PlayerConfigEntity.builder()
                .id(playerConfigData.getId())
                .stats(PlayerStatsEntity.of(playerConfigData.getStats()))
                .version(playerConfigData.getVersion())
                .scriptAddress(playerConfigData.getScriptAddress())
                .userName(playerConfigData.getUserName())
                .trip(playerConfigData.getTrip())
                .port(playerConfigData.getPort())
                .delay(playerConfigData.getDelay())
                .wait(playerConfigData.getWait())
                .dispInvCombo(playerConfigData.getDispInvCombo())
                .slowRate(playerConfigData.getSlowRate())
                .rounds(playerConfigData.getRounds())
                .message(playerConfigData.getMessage())
                .enableNet(playerConfigData.isEnableNet())
                .ignoreSlow(playerConfigData.isIgnoreSlow())
                .useEx(playerConfigData.isUseEx())
                .fpsEnable(playerConfigData.isFpsEnable())
                .broadcastEnable(playerConfigData.isBroadcastEnable())
                .intrusionEnable(playerConfigData.isIntrusionEnable())
                .watchMaxNodesEnable(playerConfigData.isWatchReplayEnable())
                .watchMaxNodesEnable(playerConfigData.isWatchMaxNodesEnable())
                .active(playerConfigData.isActive())
                .lastActivity(playerConfigData.getLastActivity())
                .build();
    }


    public void deactivate() {
        this.active = false;
    }
}

