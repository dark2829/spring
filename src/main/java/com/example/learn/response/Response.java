package com.example.learn.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {
    
    private String message; 
    private boolean isSuccess; 
    private HttpStatus http; 
    private T data;
    
    public ResponseEntity<Response<T>> responseEntity(HttpStatus http){
        return new ResponseEntity<>(this, http);
    }
    
}
