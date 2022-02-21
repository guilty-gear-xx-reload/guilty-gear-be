package ggxnet.reload.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class UserDTO {
    private String name;
    private String password;

}