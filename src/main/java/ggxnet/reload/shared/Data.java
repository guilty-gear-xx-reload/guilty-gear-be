package ggxnet.reload.shared;

import org.springframework.data.annotation.Id;

public class Data {

    @Id
    public String id;
    public String name;
    public String remote_address;
    public String port;
    public String param;
    public String win;

    @Override
    public String toString() {
        return name + "@" + remote_address + ":" + port + "%" + param + "#" + win;
    }

}
