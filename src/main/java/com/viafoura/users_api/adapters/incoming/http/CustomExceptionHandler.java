package com.viafoura.users_api.adapters.incoming.http;

import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(CustomExceptionHandler.class);


    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest req) {
        var message = "Resource not found. Docs: /swagger-ui.html";

        var body = buildResponse(message);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler({InvalidParameterException.class})
    public ResponseEntity<Object> handleException(InvalidParameterException ex) {
        var message = "Wrong parameter: " + ex.getMessage();

        var body = buildResponse(message);

        LOGGER.error(message, ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AvatarServiceException.class})
    public ResponseEntity<Object> handleException(AvatarServiceException ex) {
        var message = "Avatar Service offline";

        var body = buildResponse(message);

        LOGGER.error(message, ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RepositoryException.class})
    public ResponseEntity<Object> handleException(RepositoryException ex) {
        var message = "Problem executing DB operation";

        var body = buildResponse(message);

        LOGGER.error(message, ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        var message = "Unkown error cause";

        var body = buildResponse(message);

        LOGGER.error(message, ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //TODO: it could receive and return our own custom error codes
    private Map<String, Object> buildResponse(String errorMessage) {
        var body = new HashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());
        body.put("trace-id", MDC.get("traceId"));
        body.put("message", errorMessage);

        return body;
    }
}
