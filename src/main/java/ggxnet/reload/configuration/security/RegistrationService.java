package ggxnet.reload.configuration.security;

import ggxnet.reload.configuration.security.dto.UserDto;
import ggxnet.reload.configuration.security.entity.UserEntity;
import ggxnet.reload.configuration.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public void registerUser(UserDto newUser) {
    if (userRepository.existsByUsername(newUser.getUsername())) {
      throw new UserAlreadyExistsException(
          "User with name: " + newUser.getUsername() + " already exists");
    }
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(newUser.getUsername());
    userEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));
    userRepository.save(userEntity);
  }
}
