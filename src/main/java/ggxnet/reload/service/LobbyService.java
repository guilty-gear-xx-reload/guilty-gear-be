package ggxnet.reload.service;

import ggxnet.reload.service.operation.Operation;
import ggxnet.reload.shared.CommandType;
import ggxnet.reload.shared.ParamType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LobbyService {
    private final Map<CommandType, Operation> operations;

    public String processPost(String request, String remoteAddress) {
        var paramService = new ParamService();
        Map<ParamType, String> params = paramService.calculate(request);
        params.put(ParamType.REMOTE_ADDRESS, remoteAddress);
        var command = CommandType.valueOf(params.get(ParamType.CMD).toUpperCase());
        log.info("Actual command: {}", command);
        return operations.get(command).process(params);
    }

    public void processGet(String request) {
    }
}
