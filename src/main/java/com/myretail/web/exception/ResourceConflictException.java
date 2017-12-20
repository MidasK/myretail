
package com.myretail.web.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(CONFLICT)
public class ResourceConflictException extends RuntimeException
{
    private static final long serialVersionUID = 4207433597158863678L;

    public ResourceConflictException()
    {
        super();
    }

    public ResourceConflictException(final String message)
    {
        super(message);
    }

    public ResourceConflictException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

}
