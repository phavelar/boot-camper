package author.avelar.paulo.core.converter;

import author.avelar.paulo.rest.representation.core.ErrorsRepresentation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class MethodArgumentNotValidConverter implements Converter<MethodArgumentNotValidException, ErrorsRepresentation>
{
    @Override
    public ErrorsRepresentation convert(MethodArgumentNotValidException exception)
    {
        ErrorsRepresentation errorsRepresentation = new ErrorsRepresentation();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors)
        {
            String[] messageParts = fieldError.getDefaultMessage().split("#", 3);
            errorsRepresentation.addError(messageParts[0], messageParts[1], messageParts[2]);
        }
        return errorsRepresentation;
    }
}
