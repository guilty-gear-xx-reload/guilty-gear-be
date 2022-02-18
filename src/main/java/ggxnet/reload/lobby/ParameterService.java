package ggxnet.reload.lobby;

import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
class ParameterService {
    public Map<Parameter, String> calculate(String request) {
        if (request == null || "".equals(request)) {
            return Map.of();
        }
        Map<Parameter, String> paramsMap = new EnumMap<>(Parameter.class);
        String[] params = request.split("\\|");
        for (String s : params) {
            String[] command = s.split("=");
            paramsMap.put(Parameter.valueOf(command[0].toUpperCase()), command[1]);
        }
        log.info("Params map: {}", paramsMap);
        return paramsMap;
    }
}
