package ggxnet.reload.service.operation;

import ggxnet.reload.service.FileService;
import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static ggxnet.reload.shared.ParamType.*;

@Slf4j
public class Enter implements Operation {

    private final FileService fileService = new FileService();
    private final String configFile;
    private final Object object = new Object();

    public Enter(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public String process(Map<ParamType, String> params) {
        int port = Integer.parseInt(params.get(PORT));
        if (port < 1000 || port > 65535) {
            return "";
        }
        String address = params.get(REMOTE_ADDRESS).concat(":").concat(params.get(PORT));
        synchronized (object) {
            List<String> dataFile = fileService.readNodeList(configFile);
            String node = params.get(NAME).concat("@").concat(address);
            int index = fileService.findNode(node, dataFile);
            String newLine = Instant.now().getEpochSecond() + "$".concat(node).concat("%").concat(params.get(PARAM)).concat("#").concat(params.get(WIN));
            if (index != -1) {
                dataFile.set(index, newLine);
                log.info("Updated: " + newLine);
            } else {
                dataFile.add(newLine);
                log.info("Added: " + newLine);
            }
            fileService.saveAll(dataFile, configFile);
            return "##head##" + address + "##foot##\r\n";
        }

    }
}
