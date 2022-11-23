package ggxnet.reload.service.dto;

import java.util.Objects;

public final class UserDto {
  private final String name;
  private final String password;

  public UserDto(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public UserDto() {
    this(null, null);
  }

  public String name() {
    return name;
  }

  public String password() {
    return password;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (UserDto) obj;
    return Objects.equals(this.name, that.name) && Objects.equals(this.password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, password);
  }

  @Override
  public String toString() {
    return "UserDto[" + "name=" + name + ", " + "password=" + password + ']';
  }
}
