package author.avelar.paulo.rest.resource;


import author.avelar.paulo.Application;
import author.avelar.paulo.rest.representation.domain.Song;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static author.avelar.paulo.rest.representation.domain.MusicGenre.ROCK;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
public class SongsResourceTest
{
    @Value("${local.server.port}")
    private int port;
    private Song validSong;
    private Song invalidSong;

    @Before
    public void setup()
    {
        RestAssured.port = port;
        validSong = new Song(
            "Closer to Home",
            598,
            "http://www.grandfunkrailroad.com/",
            "http://www.lyricsfreak.com/g/grand+funk+railroad/closer+to+home_20169845.html",
            ROCK
        );
        invalidSong = new Song("not a good song", 1000, "aBadUrl", "anotherBadUrl", null);
    }

    @Test
    public void create_withValidJson_returnsCreatedStatusCode()
    {
        given()
            .contentType(JSON)
            .body(validSong)
        .when()
            .post("/songs/song")
        .then()
            .statusCode(CREATED.value());
    }

    @Test
    public void create_withInvalidJson_returnsBadRequestStatusCode()
    {
        given()
            .contentType(JSON)
            .body(invalidSong)
        .when()
            .post("/songs/song")
        .then()
            .statusCode(BAD_REQUEST.value())
            .body("errors.size()", is(3)).and()
            .body("errors.code", hasItems("format", "invalid")).and()
            .body("errors.field", hasItems("artistUrl", "lyricsUrl", "musicGenre")).and()
            .body("errors.message", hasItems("must be a valid URL", "must not be null"));
    }


}