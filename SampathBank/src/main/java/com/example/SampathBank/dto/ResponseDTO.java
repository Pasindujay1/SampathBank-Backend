package com.example.SampathBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTO {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    private Object content;
}
