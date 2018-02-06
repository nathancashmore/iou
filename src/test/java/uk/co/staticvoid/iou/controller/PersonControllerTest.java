package uk.co.staticvoid.iou.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uk.co.staticvoid.iou.conveyor.request.AddPersonRequest;
import uk.co.staticvoid.iou.conveyor.response.AddPersonResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    private static final String NICKNAME = "MonkeyBoy";

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Test
    public void createAndReadPersonDetails() throws Exception {
        AddPersonRequest addPersonRequest = new AddPersonRequest(NICKNAME);

        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post("/person")
            .content(gson.toJson(addPersonRequest))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        String createdPersonUuid = gson.fromJson(createResult.getResponse().getContentAsString(), AddPersonResponse.class).getUuid();

        mvc.perform(MockMvcRequestBuilders.get("/person/" + createdPersonUuid)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("nickname").value(NICKNAME));
    }

}