package ggxnet.reload.service;

import ggxnet.reload.repository.UserRepository;
import ggxnet.reload.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {

  @Autowired private UserRepository customerRepository;

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
