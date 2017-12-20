
package com.myretail.web.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException
{
    private static final long serialVersionUID = 5429661933090903760L;

    public ResourceNotfoundException()
    {
        super();
    }

    public ResourceNotfoundException(final String message)
    {
        super(message);
    }

}
