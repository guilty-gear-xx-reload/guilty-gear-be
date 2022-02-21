package ggxnet.reload.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@AllArgsConstructor
public class PlayerSettingsEntity {
    @Id
    private String id;
    private int ver;
    private String scriptAddress;
    private List<String> addressList;
    private String userName;
    private String trip;
    private String enableNet;
    private short port;
    private String delay;
    private String ignoreMisNode;
    private String ignoreSlow;
    private short wait;
    private String useEx;
    private String dispInvCombo;
    private String showfps;
    private short wins;
    private String rank;
    private Integer score;
    private Integer totalBattle;
    private Integer totalWin;
    private Integer totalLose;
    private Integer totalDraw;
    private Integer totalError;
    private Integer slowRate;
    private String rounds;
    private String msg;
    private String watchBroadcast;
    private String watchIntrusion;
    private String watchSaveReplay;
    private String watchMaxNodes;




}

