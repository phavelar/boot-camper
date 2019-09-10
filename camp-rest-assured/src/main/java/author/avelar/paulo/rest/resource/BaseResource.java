package author.avelar.paulo.rest.resource;

import author.avelar.paulo.rest.representation.core.ErrorsRepresentation;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.inject.Inject;
import javax.inject.Named;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Named
public class BaseResource
{
    @Inject
    protected ConversionService conversionService;

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(NOT_ACCEPTABLE)
    public void handleMediaTypeNotAcceptable()
    {
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorsRepresentation handleValidationExceptions(MethodArgumentNotValidException exception)
    {
        return conversionService.convert(exception, ErrorsRepresentation.class);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorsRepresentation handleMissingServletRequestParameterException(MissingServletRequestParameterException exception)
    {
        return conversionService.convert(exception, ErrorsRepresentation.class);
    }
}
