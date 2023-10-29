package org.example.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Jacksonized
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeetingResponseDto implements Serializable {

    @JsonProperty("join_url")
    private String joinUrl;
}