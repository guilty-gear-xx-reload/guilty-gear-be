package ggxnet.reload.controller;

import java.io.Serializable;

public record EnterResponse(String scriptAddress, int port) implements Serializable {}
