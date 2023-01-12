package ggxnet.reload.controller.response;

import java.io.Serializable;

public record EnterResponse(String scriptAddress, int port) implements Serializable {}
