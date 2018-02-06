package uk.co.staticvoid.iou;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.staticvoid.iou.conveyor.request.AddPersonRequest;
import uk.co.staticvoid.iou.conveyor.response.AddPersonResponse;
import uk.co.staticvoid.iou.conveyor.response.GetPersonResponse;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IouApplicationTests {

	private static final String PERSON_PATH = "person";
	private static final String DOC_PATH = "swagger-ui.html";

	private static final String NICKNAME = "CrazyLegs";

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void addThenGetPerson() throws Exception {
		String addPersonUrl = this.base.toString() + PERSON_PATH;

		HttpEntity<AddPersonRequest> addPersonRequest = new HttpEntity<>(new AddPersonRequest(NICKNAME));
		AddPersonResponse addPersonResponse = template.postForObject(addPersonUrl, addPersonRequest, AddPersonResponse.class);

		assertThat(addPersonResponse.getUuid()).isNotEmpty();

		String getPersonUrl = this.base.toString() + PERSON_PATH + "/" + addPersonResponse.getUuid();
		GetPersonResponse getPersonResponse = template.getForObject(getPersonUrl, GetPersonResponse.class);

		assertThat(getPersonResponse.getNickname()).isEqualTo(NICKNAME);
	}

	@Test
	public void	getDocumentation() throws Exception {
		String docsUrl = this.base.toString() + DOC_PATH;

		ResponseEntity<String> response = template.getForEntity(docsUrl, String.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

}
