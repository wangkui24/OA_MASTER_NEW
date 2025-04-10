package com.kwang43.boot.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptchaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String image;

    private String uuid;
}
