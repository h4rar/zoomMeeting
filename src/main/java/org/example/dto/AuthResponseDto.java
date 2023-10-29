package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Builder
@Jacksonized
@Data
public class AuthResponseDto implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;
}
