package author.avelar.paulo.rest.resource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
@PropertySource("/application.properties")
public class VersionResource
{
    @Value("${application.version}")
    private String version;

    @RequestMapping(produces = "text/html")
    public String version()
    {
        return version;
    }
}
