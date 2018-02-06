package uk.co.staticvoid.iou.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;


@DataJpaTest
@EnableWebMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonRepositoryTest {

    private static final String NICKNAME = "TestNickname";
    private static final String UUID = java.util.UUID.randomUUID().toString();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldFindPersonByNickname() {
        Person person = entityManager.persist(new Person(NICKNAME));
        Person result = personRepository.findByNickname(person.getNickname());
        assertThat(result.getNickname()).isEqualTo(NICKNAME);
    }

    @Test
    public void shouldFindPersonbyUuid() {
        Person person = entityManager.persist(new Person(UUID, NICKNAME));
        Optional<Person> result = personRepository.findByUuid(person.getUuid());

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getUuid()).isEqualTo(UUID);
        assertThat(result.get().getNickname()).isEqualTo(NICKNAME);
    }
}