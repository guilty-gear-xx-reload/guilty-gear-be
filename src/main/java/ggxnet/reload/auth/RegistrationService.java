package ggxnet.reload.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(UserDTO newUser) {
        if (userRepository.existsByUsername(newUser.getName())) {
            throw new UserAlreadyExistsException("User with name: " + newUser.getName() + " already exists");
        }
        User user = new User();
        user.setUsername(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }
}
