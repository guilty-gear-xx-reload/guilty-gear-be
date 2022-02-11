package ggxnet.reload;

import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
class ParamService {
    public Map<ParamType, String> calculate(String input) {
        if (input == null || "".equals(input)) {
            return Map.of();
        }
        Map<ParamType, String> paramsMap = new EnumMap<>(ParamType.class);
        String[] params = input.split("\\|");
        for (String s : params) {
            String[] command = s.split("=");
            paramsMap.put(ParamType.valueOf(command[0].toUpperCase()), command[1]);
        }
        log.info("Params map: {}", paramsMap);
        return paramsMap;
    }
}
