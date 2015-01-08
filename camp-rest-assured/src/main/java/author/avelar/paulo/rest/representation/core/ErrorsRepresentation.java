package author.avelar.paulo.rest.representation.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ErrorsRepresentation
{
    private final List<ErrorRepresentation> errorRepresentations = new ArrayList<ErrorRepresentation>();

    @JsonProperty("errors")
    public List<ErrorRepresentation> getErrorRepresentations()
    {
        return errorRepresentations;
    }

    public void addError(String field, String code, String message)
    {
        errorRepresentations.add(new ErrorRepresentation(field, code, message));
    }

}
