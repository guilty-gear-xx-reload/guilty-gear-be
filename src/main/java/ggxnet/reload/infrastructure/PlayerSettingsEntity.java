package ggxnet.reload.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@AllArgsConstructor
@Builder
public class PlayerSettingsEntity {
    @Id
    private String id;
    private int ver;
    private String scriptAddress;
    private String userName;
    private String trip;
    private Integer enableNet;
    private Integer port;
    private Integer delay;
    private Integer ignoreMisNode;
    private Integer ignoreSlow;
    private Integer wait;
    private Integer useEx;
    private Integer dispInvCombo;
    private Integer showfps;
    private Integer wins;
    private Integer rank;
    private Integer score;
    private Integer totalBattle;
    private Integer totalWin;
    private Integer totalLose;
    private Integer totalDraw;
    private Integer totalError;
    private Integer slowRate;
    private Integer rounds;
    private String msg;
    private Integer watchBroadcast;
    private Integer watchIntrusion;
    private Integer watchSaveReplay;
    private Integer watchMaxNodes;

    @Override
    public String toString() {
        return ver +
                "|" + scriptAddress +
                "|" + userName +
                "|" + trip +
                "|" + enableNet +
                "|" + port +
                "|" + delay +
                "|" + ignoreMisNode +
                "|" + ignoreSlow +
                "|" + 3 +
                "|" + useEx +
                "|" + dispInvCombo +
                "|" + showfps +
                "|" + wins +
                "|" + rank +
                "|" + score +
                "|" + totalBattle +
                "|" + totalWin +
                "|" + totalLose +
                "|" + totalDraw +
                "|" + totalError +
                "|" + slowRate +
                "|" + rounds +
                "|" + msg +
                "|" + watchBroadcast +
                "|" + watchIntrusion +
                "|" + watchSaveReplay +
                "|" + watchMaxNodes
                + "|";

    }
}

