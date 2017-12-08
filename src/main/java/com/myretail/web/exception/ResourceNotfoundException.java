
package com.myretail.web.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException
{

    public ResourceNotfoundException()
    {
        super();
    }

    public ResourceNotfoundException(final String message)
    {
        super(message);
    }

    public ResourceNotfoundException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

}
