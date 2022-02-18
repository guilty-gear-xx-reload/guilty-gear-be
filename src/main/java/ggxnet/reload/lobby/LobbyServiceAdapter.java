package ggxnet.reload.lobby;

import ggxnet.reload.lobby.port.incoming.LobbyServicePort;
import ggxnet.reload.shared.CommandType;
import ggxnet.reload.shared.ParamType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
class LobbyServiceAdapter implements LobbyServicePort {
    private final Map<CommandType, Operation> operations;

    @Override
    public String processPost(String parsedRequest, String remoteAddress) {
        var paramService = new ParamService();
        Map<ParamType, String> params = paramService.calculate(parsedRequest);
        params.put(ParamType.REMOTE_ADDRESS, remoteAddress);
        var command = CommandType.valueOf(params.get(ParamType.CMD).toUpperCase());
        log.info("Actual command: {}", command);
        return operations.get(command).process(params);
    }

    @Override
    public void processGet(String request) {
    }
}
