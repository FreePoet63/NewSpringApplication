package ApplicationAPI.Configuration;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NewApplication {
    @GetMapping("/users")
    public String message() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(new HttpGet("https://api.stackexchange.com/2.2/answers?site" +
                "=stackoverflow&page=1&pagesize=10&order=desc&sort=activity&filter=default"));
        HttpEntity entity = response.getEntity();
        String data = IOUtils.toString(entity.getContent(), "UTF-8");
        System.out.println("Data: " + data);
        return data;
    }

}
