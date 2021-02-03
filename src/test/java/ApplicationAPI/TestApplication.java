package ApplicationAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.properties")
public class TestApplication {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void newApplicationTest() throws Exception {
        this.mockMvc.perform(get("/users/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("items", hasSize(10)))
                .andExpect(jsonPath("items[*].owner", hasSize(10)));
    }

    @Test
    public void linksApplication() {
        given()
                .queryParam("owner.user_id", "1367454")
                .queryParam("owner.display_name", "eshirvana")
        .when()
                .get("https://stackoverflow.com/users/1367454/eshirvana")
        .then()
                .log().body();

    }
}