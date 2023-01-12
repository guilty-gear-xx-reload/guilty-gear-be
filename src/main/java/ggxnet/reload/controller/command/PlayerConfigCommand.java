package ggxnet.reload.controller.command;

public class PlayerConfigCommand {
  private final String scriptAddress;
  private final String userName;
  private final String trip;
  private final Integer port;
  private final Integer delay;
  private final boolean ignoreMisNode;
  private final boolean ignoreSlow;
  private final Integer wait;
  private final boolean useEx;
  private final Integer dispInvCombo;
  private final boolean showfps;
  private final Integer slowRate;
  private final Integer rounds;
  private final String msg;
  private final boolean watchBroadcast;
  private final boolean watchIntrusion;
  private final boolean watchSaveReplay;
  private final boolean watchMaxNodes;

  public PlayerConfigCommand(
      String scriptAddress,
      String userName,
      String trip,
      Integer port,
      Integer delay,
      boolean ignoreMisNode,
      boolean ignoreSlow,
      Integer wait,
      boolean useEx,
      Integer dispInvCombo,
      boolean showfps,
      Integer slowRate,
      Integer rounds,
      String msg,
      boolean watchBroadcast,
      boolean watchIntrusion,
      boolean watchSaveReplay,
      boolean watchMaxNodes) {
    this.scriptAddress = scriptAddress;
    this.userName = userName;
    this.trip = trip;
    this.port = port;
    this.delay = delay;
    this.ignoreMisNode = ignoreMisNode;
    this.ignoreSlow = ignoreSlow;
    this.wait = wait;
    this.useEx = useEx;
    this.dispInvCombo = dispInvCombo;
    this.showfps = showfps;
    this.slowRate = slowRate;
    this.rounds = rounds;
    this.msg = msg;
    this.watchBroadcast = watchBroadcast;
    this.watchIntrusion = watchIntrusion;
    this.watchSaveReplay = watchSaveReplay;
    this.watchMaxNodes = watchMaxNodes;
  }

  public String getScriptAddress() {
    return scriptAddress;
  }

  public String getUserName() {
    return userName;
  }

  public String getTrip() {
    return trip;
  }

  public Integer getPort() {
    return port;
  }

  public Integer getDelay() {
    return delay;
  }

  public boolean isIgnoreMisNode() {
    return ignoreMisNode;
  }

  public boolean isIgnoreSlow() {
    return ignoreSlow;
  }

  public Integer getWait() {
    return wait;
  }

  public boolean isUseEx() {
    return useEx;
  }

  public Integer getDispInvCombo() {
    return dispInvCombo;
  }

  public boolean isShowfps() {
    return showfps;
  }

  public Integer getSlowRate() {
    return slowRate;
  }

  public Integer getRounds() {
    return rounds;
  }

  public String getMsg() {
    return msg;
  }

  public boolean isWatchBroadcast() {
    return watchBroadcast;
  }

  public boolean isWatchIntrusion() {
    return watchIntrusion;
  }

  public boolean isWatchSaveReplay() {
    return watchSaveReplay;
  }

  public boolean isWatchMaxNodes() {
    return watchMaxNodes;
  }
}
