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
public class CreateMeetingRequestDto implements Serializable {

    // Название. максимум 200 символов
    @JsonProperty("topic")
    private String topic;

    // Время начала
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("start_time")
    private LocalDateTime startTime;

    // Доступные варианты:
    // https://developers.zoom.us/docs/api/rest/other-references/abbreviation-lists/#timezones
    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("settings")
    private MeetingSettings settings;

}