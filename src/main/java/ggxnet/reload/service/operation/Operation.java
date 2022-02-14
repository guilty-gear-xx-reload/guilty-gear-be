package ggxnet.reload.service.operation;

import ggxnet.reload.shared.ParamType;

import java.util.Map;

public interface Operation {
    String process(Map<ParamType, String> params);
}
