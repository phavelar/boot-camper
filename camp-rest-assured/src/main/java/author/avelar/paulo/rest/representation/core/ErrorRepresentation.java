package author.avelar.paulo.rest.representation.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import static org.apache.commons.lang3.StringUtils.trim;

@JsonPropertyOrder({"code", "field", "message"})
public final class ErrorRepresentation
{
    private final String field;
    private final String code;
    private final String message;

    @JsonCreator
    public ErrorRepresentation(@JsonProperty("field") String field,
                               @JsonProperty("code") String code,
                               @JsonProperty("message") String message)
    {
        this.field = trim(field);
        this.code = trim(code);
        this.message = trim(message);
    }

    public String getField()
    {
        return field;
    }

    public String getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }
}
