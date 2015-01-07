package author.avelar.paulo.rest.resource;


import author.avelar.paulo.Application;
import author.avelar.paulo.rest.representation.Song;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static author.avelar.paulo.rest.representation.MusicGenre.ROCK;
import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SongsResourceTest
{
    @Value("${local.server.port}")
    private int port;

    @Before
    public void setup()
    {
        RestAssured.port = port;
    }

    @Test
    public void create_withValidJSON_returnsCreatedStatusCode()
    {
        Song song = new Song(
            "Closer to Home",
            598,
            "http://www.grandfunkrailroad.com/",
            "http://www.lyricsfreak.com/g/grand+funk+railroad/closer+to+home_20169845.html",
            ROCK
        );

        given()
            .contentType(ContentType.JSON)
            .body(song)
            .when()
            .post("/songs/song")
            .then()
            .statusCode(HttpStatus.CREATED.value());
    }


}