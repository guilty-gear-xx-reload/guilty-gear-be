package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;

import java.util.Map;

interface Operation {
    String process(Map<ParamType, String> params);
}
