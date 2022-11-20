package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
  UserEntity findByUsername(String username);

  boolean existsByUsername(String username);
}
