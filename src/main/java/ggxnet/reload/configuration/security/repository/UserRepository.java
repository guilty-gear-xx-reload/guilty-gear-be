package ggxnet.reload.configuration.security.repository;

import ggxnet.reload.configuration.security.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
  UserEntity findByUsername(String username);

  boolean existsByUsername(String username);
}
