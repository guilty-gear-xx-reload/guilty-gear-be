package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.CharacterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterEntity, Long> {
    Optional<CharacterEntity> findByName(String name);
}