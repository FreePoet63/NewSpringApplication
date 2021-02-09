package ApplicationAPI;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
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
        MvcResult result = this.mockMvc.perform(get("/users/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("items", hasSize(10)))
                .andExpect(jsonPath("items[*].owner", hasSize(10)))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        List<String> link = JsonPath.read(content, "items[*].owner.link");
        List<Integer> user_id = JsonPath.read(content, "items[*].owner.user_id");
        List<String> display_name = JsonPath.read(content, "items[*].owner.display_name");
        Assert.assertEquals(link.get(0),"https://stackoverflow.com/users/" + user_id.get(0) +
                "/" + display_name.get(0).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(1),"https://stackoverflow.com/users/" + user_id.get(1) +
                "/" + display_name.get(1).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(2),"https://stackoverflow.com/users/" + user_id.get(2) +
                "/" + display_name.get(2).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(3),"https://stackoverflow.com/users/" + user_id.get(3) +
                "/" + display_name.get(3).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(4),"https://stackoverflow.com/users/" + user_id.get(4) +
                "/" + display_name.get(4).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(5),"https://stackoverflow.com/users/" + user_id.get(5) +
                "/" + display_name.get(5).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(6),"https://stackoverflow.com/users/" + user_id.get(6) +
                "/" + display_name.get(6).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(7),"https://stackoverflow.com/users/" + user_id.get(7) +
                "/" + display_name.get(7).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(8),"https://stackoverflow.com/users/" + user_id.get(8) +
                "/" + display_name.get(8).toLowerCase().replace(" ", "-"));
        Assert.assertEquals(link.get(9),"https://stackoverflow.com/users/" + user_id.get(9) +
                "/" + display_name.get(9).toLowerCase().replace(" ", "-"));
    }
}