package ggxnet.reload.configuration.security;

import ggxnet.reload.configuration.security.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DBUserDetailsService implements UserDetailsService {

  private final UserRepository customerRepository;

  public DBUserDetailsService(UserRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final UserEntity userEntity = customerRepository.findByUsername(username);
    if (userEntity == null) {
      throw new UsernameNotFoundException(username);
    }
    return User.withUsername(userEntity.getUsername())
        .password(userEntity.getPassword())
        .authorities("USER")
        .build();
  }
}
