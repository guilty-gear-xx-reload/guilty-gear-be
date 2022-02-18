package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static ggxnet.reload.shared.ParamType.*;

@Slf4j
class Leave implements Operation {

    private final FileService fileService = new FileService();
    private final String configFile;
    private final Object object = new Object();

    public Leave(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public String process(Map<ParamType, String> params) {
        String address = params.get(REMOTE_ADDRESS).concat(":").concat(params.get(PORT));
        if (address.equals("0")) {
            return "";
        }
        synchronized (object) {
            var dataFile = fileService.readNodeList(configFile);

            while (true) { // nie jestem pewien czy to jest potrzebne, to jest chyba swego rodzaju zabezpieczenie przed tym że np. zdublowane są rekordy w pliku
                String ownNode = params.get(NAME).concat("@").concat(address);
                int index = fileService.findNode(ownNode, dataFile);
                if (index == -1) {
                    break;
                }
                dataFile.remove(index);
                log.info("Leaved: " + ownNode);
            }

            fileService.saveAll(dataFile, configFile);
            return "";
        }

    }
}
