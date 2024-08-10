package com.example.learn.config;

import com.example.learn.response.GeneralServiceException;
import com.example.learn.response.NoDataFoundException;
import com.example.learn.response.Response;
import com.example.learn.response.ValidateServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request){
        log.error(e.getMessage(), e);
        Response<?> response = new Response("Recurso no encontrado", false, HttpStatus.INTERNAL_SERVER_ERROR, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateService(ValidateServiceException e, WebRequest request){
        log.error(e.getMessage(), e);        
        Response<?> response = new Response(e.getMessage(), false, HttpStatus.UNSUPPORTED_MEDIA_TYPE, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validarDatos(MethodArgumentNotValidException e, WebRequest request){
        log.error(e.getMessage());
        //Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String key = ((FieldError) error ).getField();
            String val = error.getDefaultMessage();
            errors.put(key, val);
        });
        
        //Response<?> response = new Response(e.getMessage(), false, HttpStatus.BAD_REQUEST, null); 
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }*/
    
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFound(NoDataFoundException e, WebRequest request){
        log.error(e.getMessage(), e);
        Response<?> response = new Response(e.getMessage(), false, HttpStatus.NOT_FOUND, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> general(GeneralServiceException e, WebRequest request){
        log.error(e.getMessage(), e);
        Response<?> response = new Response(e.getMessage(), false, HttpStatus.UNSUPPORTED_MEDIA_TYPE, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
