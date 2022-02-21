package ggxnet.reload.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

}