package ggxnet.reload.lobby.client.command;

import lombok.Value;

@Value
public class PlayerConfigCommand {
    String scriptAddress;
    String userName;
    String trip;
    Integer port;
    Integer delay;
    boolean ignoreMisNode;
    boolean ignoreSlow;
    Integer wait;
    boolean useEx;
    Integer dispInvCombo;
    boolean showfps;
    Integer slowRate;
    Integer rounds;
    String msg;
    boolean watchBroadcast;
    boolean watchIntrusion;
    boolean watchSaveReplay;
    boolean watchMaxNodes;
}
