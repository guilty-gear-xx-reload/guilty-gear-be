package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
class ParamService {
    public Map<ParamType, String> calculate(String request) {
        if (request == null || "".equals(request)) {
            return Map.of();
        }
        Map<ParamType, String> paramsMap = new EnumMap<>(ParamType.class);
        String[] params = request.split("\\|");
        for (String s : params) {
            String[] command = s.split("=");
            paramsMap.put(ParamType.valueOf(command[0].toUpperCase()), command[1]);
        }
        log.info("Params map: {}", paramsMap);
        return paramsMap;
    }
}
