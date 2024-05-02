package org.example.bookmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private Boolean success = true;
    private String message;
    private int code = 200;
    private Object data;
    public ServiceResponse(String message) {
        this.message = message;
    }

    public ServiceResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ServiceResponse(Boolean status, Object data) {
        this.success = false;
        this.message = message;
        this.data = data;
    }

    public ServiceResponse(Boolean success, Integer code, String message) {
        this.success = success;
        this.message = message;
        this.code = code;
    }
}
