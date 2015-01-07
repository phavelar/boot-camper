package author.avelar.paulo.rest.resource;

import author.avelar.paulo.Application;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class VersionResourceTest
{
    @Value("${application.version}")
    private String version;
    @Value("${local.server.port}")
    private int port;

    @Before
    public void setup()
    {
        RestAssured.port = port;
    }

    @Test
    public void testVersion()
    {
        Response response = given().contentType("text/html").when().get("/version");

        assertThat(response.asString()).isEqualTo(version);
        assertThat(response.statusCode()).isEqualTo(OK.value());
    }
}