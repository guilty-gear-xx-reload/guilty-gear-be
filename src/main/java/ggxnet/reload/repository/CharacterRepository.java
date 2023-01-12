package ggxnet.reload.repository;

import ggxnet.reload.repository.entity.CharacterEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterEntity, Long> {
  Optional<CharacterEntity> findByName(String name);
}
