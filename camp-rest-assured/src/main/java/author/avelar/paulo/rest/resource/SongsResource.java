package author.avelar.paulo.rest.resource;

import author.avelar.paulo.rest.representation.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/songs")
public class SongsResource
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/song", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final Song song)
    {
        logger.info("created song: {}", song);
    }

}
