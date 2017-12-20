
package com.myretail.web.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationExceptionResolver
{

    @JsonProperty
    private String statusCode;

    @JsonProperty
    private HttpStatus status;

    @JsonProperty
    private String timestamp;

    @JsonProperty
    private String message;

    @JsonProperty
    private List<String> stackTrace;

    ApplicationExceptionResolver()
    {
    }

    ApplicationExceptionResolver(final HttpStatus status, final Throwable ex)
    {
        this.status = status;
        this.statusCode = String.valueOf(status.value());
        this.message = ex.getMessage();
        this.stackTrace = getStackTrace(ex.getStackTrace());
        this.timestamp = getTimeStamp();
    }

    ApplicationExceptionResolver(final HttpStatus status, final String message, final Throwable ex)
    {
        this.status = status;
        this.statusCode = String.valueOf(status.value());
        this.message = message;
        this.stackTrace = getStackTrace(ex.getStackTrace());
        this.timestamp = getTimeStamp();
    }

    protected List<String> getStackTrace(final StackTraceElement[] stackTraceElementArray)
    {
        List<String> stackTrace = new ArrayList<>();
        for (StackTraceElement element : stackTraceElementArray)
        {
            stackTrace.add(element.toString());
        }

        return stackTrace;
    }

    protected String getTimeStamp()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public HttpStatus getStatus()
    {
        return status;
    }

    public void setStatus(final HttpStatus status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(final String statusCode)
    {
        this.statusCode = statusCode;
    }

    public List<String> getStackTrace()
    {
        return stackTrace;
    }

    public void setStackTrace(final List<String> stackTrace)
    {
        this.stackTrace = stackTrace;
    }

}
