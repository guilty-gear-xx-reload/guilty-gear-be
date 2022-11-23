package ggxnet.reload.service;

import ggxnet.reload.repository.UserRepository;
import ggxnet.reload.repository.entity.UserEntity;
import ggxnet.reload.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public void registerUser(UserDto newUser) {
    if (userRepository.existsByUsername(newUser.name())) {
      throw new UserAlreadyExistsException("User with name: " + newUser.name() + " already exists");
    }
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(newUser.name());
    userEntity.setPassword(passwordEncoder.encode(newUser.password()));
    userRepository.save(userEntity);
  }
}
