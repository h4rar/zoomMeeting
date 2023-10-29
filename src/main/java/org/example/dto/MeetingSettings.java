package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Builder
@Jacksonized
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSettings implements Serializable {

    // Разрешить участникам присоединяться раньше организатора
    @JsonProperty("join_before_host")
    private boolean joinBeforeHost;

    // Включить комнату ожидания
    @JsonProperty("waiting_room")
    private boolean waitingRoom;

    // Включить камеру у участника, запустившего митинг
    @JsonProperty("host_video")
    private boolean hostVideo;

    // Включить камеру у всех, кто присоединяется
    @JsonProperty("participant_video")
    private boolean participantVideo;

    //Включить одобрение регистрации на собрание.
    //
    //0 - автоматически утверждать регистрацию.
    //1 - Подтвердить регистрацию вручную.
    //2 - Регистрация не требуется.
    @JsonProperty("approval_type")
    private int approvalType;

    //Тип регистрации собрания.
    //
    //1 - Участники регистрируются один раз и могут присутствовать на любом совещании.
    //2 - Участники должны регистрироваться для каждого собрания.
    //3 - Участники регистрируются один
    @JsonProperty("registration_type")
    private int registrationType;

    //Как участники могут присоединиться к аудиочасти собрания.
    //both  - И telephony, и VoIP.
    //telephony  – только телефония.
    //voip  – только VoIP.
    //ThirdParty — сторонняя аудиоконференция
    @JsonProperty("audio")
    private String audio;

    //Автоматическая запись.
    //local - Запись на локальном.
    //cloud — запись в облако.
    //none – отключено.
    @JsonProperty("auto_recording")
    private String autoRecording;

    //Отправлять зарегестрированным пользователям уведомление на почту
    @JsonProperty("registrants_email_notification")
    private boolean registrantsEmailNotification;

    //Присоединяться к собранию могут только аутентифицированные пользователи
    @JsonProperty("meeting_authentication")
    private boolean meetingAuthentication;
}
