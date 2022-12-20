package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
  UserEntity findByUsername(String username);

  boolean existsByUsername(String username);
}
