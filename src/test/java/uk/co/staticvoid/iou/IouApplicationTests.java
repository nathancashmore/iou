package uk.co.staticvoid.iou;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IouApplicationTests {

	public static final String USER_PATH = "user";
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
	public void getUserNotSpecified() throws Exception {
		String userUrl = this.base.toString() + USER_PATH;
		JSONObject expected = new JSONObject().put("name", "Stranger");

		ResponseEntity<String> response = template.getForEntity(userUrl, String.class);
		assertThat(response.getBody(), equalTo(expected.toString()));
	}

    @Test
    public void getUser() throws Exception {
        String userUrl = this.base.toString() + USER_PATH + "?name=Bob";
        JSONObject expected = new JSONObject().put("name", "Bob");

        ResponseEntity<String> response = template.getForEntity(userUrl, String.class);
        assertThat(response.getBody(), equalTo(expected.toString()));
    }

}
