package author.avelar.paulo.rest.resource;

import author.avelar.paulo.Application;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

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
        given()
            .get("/version")
            .then()
            .body("version", equalTo(version));
    }

}