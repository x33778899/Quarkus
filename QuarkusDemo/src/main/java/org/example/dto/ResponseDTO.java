package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDTO {
    private int statusCode;
    private String message;
    @JsonbProperty(nillable = true)
    private Object data;
}
