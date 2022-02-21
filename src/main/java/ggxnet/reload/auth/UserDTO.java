package ggxnet.reload.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
class UserDTO {
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    private String password;

    public UserDTO() {
    }

}