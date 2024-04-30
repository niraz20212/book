package org.example.bookmanagementsystem.exceptionhandler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private Boolean success=false;
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
