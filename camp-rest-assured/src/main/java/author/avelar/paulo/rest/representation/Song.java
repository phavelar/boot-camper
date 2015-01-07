package author.avelar.paulo.rest.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by paulo on 1/3/2015.
 */
@JsonPropertyOrder({"name", "durationInSeconds", "artistUrl", "lyricsUrl", "musicGenre"})
public final class Song
{
    private final String name;
    private final Integer durationInSeconds;
    private final String artistUrl;
    private final String lyricsUrl;
    private final MusicGenre musicGenre;

    @JsonCreator
    public Song(@JsonProperty("name") String name,
                @JsonProperty("durationInSeconds") Integer durationInSeconds,
                @JsonProperty("artistUrl") String artistUrl,
                @JsonProperty("lyricsUrl") String lyricsUrl,
                @JsonProperty("musicGenre") MusicGenre musicGenre)
    {
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.artistUrl = artistUrl;
        this.lyricsUrl = lyricsUrl;
        this.musicGenre = musicGenre;
    }

    @Size(min = 1, max = 255)
    @NotEmpty
    public String getName()
    {
        return name;
    }


    @Max(5410)
    // to allow for the longest song ever
    // @see http://www.guinnessworldrecords.com/world-records/longest-officially-released-song
    public Integer getDurationInSeconds()
    {
        return durationInSeconds;
    }

    @URL
    public String getArtistUrl()
    {
        return artistUrl;
    }

    @URL
    public String getLyricsUrl()
    {
        return lyricsUrl;
    }

    @NotNull
    public MusicGenre getMusicGenre()
    {
        return musicGenre;
    }
}
