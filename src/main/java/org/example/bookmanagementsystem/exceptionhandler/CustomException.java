package org.example.bookmanagementsystem.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private Boolean success=false;
    private String message;
    private String code;
    private int statusCode;
    public CustomException(String code) {
        this.code = code;
    }
    public CustomException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    public CustomException(int statusCode, String code, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.code = code;
    }

}
