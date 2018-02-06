package uk.co.staticvoid.iou.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByNickname(@Param("nickname") String nickname);

    Optional<Person> findByUuid(@Param("uuid") String uuid);

}
