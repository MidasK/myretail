
package com.myretail.web.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(ResourceNotfoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(final ResourceNotfoundException ex)
    {
        ApplicationExceptionResolver resourceNotfoundError = new ApplicationExceptionResolver(ex.getClass().getAnnotation(ResponseStatus.class).value(), ex);
        return buildResponseEntity(resourceNotfoundError);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<Object> handleDuplicateKey(final ResourceConflictException ex)
    {
        ApplicationExceptionResolver duplicateKeyException = new ApplicationExceptionResolver(ex.getClass().getAnnotation(ResponseStatus.class).value(), ex);
        return buildResponseEntity(duplicateKeyException);
    }

    private ResponseEntity<Object> buildResponseEntity(final ApplicationExceptionResolver applicationError)
    {
        return new ResponseEntity<>(applicationError, applicationError.getStatus());
    }
}
