package uk.co.staticvoid.iou.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uk.co.staticvoid.iou.conveyor.request.AddPersonRequest;
import uk.co.staticvoid.iou.conveyor.request.GetPersonRequest;
import uk.co.staticvoid.iou.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@EnableWebMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    private static final String NICKNAME = "testNickname";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PersonService personService;

    @Test
    public void shouldAddAPerson() {
        Person addedPerson = personService.addPerson(new AddPersonRequest(NICKNAME));
        assertThat(addedPerson.getNickname()).isEqualTo(NICKNAME);
    }

    @Test
    public void shouldGetAPerson() {
        Person addedPerson = personService.addPerson(new AddPersonRequest(NICKNAME));
        Person retrievedPerson = personService.getPersonByUuid(new GetPersonRequest(addedPerson.getUuid()));

        assertThat(retrievedPerson.getNickname()).isEqualTo(NICKNAME);
        assertThat(retrievedPerson.getId()).isEqualTo(addedPerson.getId());
    }
}