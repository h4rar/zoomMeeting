package org.example;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.example.dto.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.*;
import java.util.*;

public class App {

    public static final String ACCOUNT_ID = "";

    public static final String CLIENT_ID = "";

    public static final String CLIENT_SECRET = "";

    public static final String BASE_URL = "https://api.zoom.us/v2";

    public static final String OAUTH_PATH = "https://zoom.us/oauth/token";

    public static void main(String[] args) {
        String name = "topicName001";
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 20, 10, 59, 44);
        String timezone = "Europe/Moscow"; // Доступные значения https://developers.zoom.us/docs/api/rest/other-references/abbreviation-lists/#timezones

        String meetingUrl = createMeeting(name, startTime, timezone);
        System.out.println(meetingUrl);
    }

    private static String createMeeting(String name, LocalDateTime startTime, String timezone){
        String accessToken = getAccessToken();
        CreateMeetingRequestDto requestDto = buildRequestDto(name, startTime, timezone);
        return createMeeting(accessToken, requestDto);
    }

    private static String getAccessToken() {
        String token = buildToken();
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        WebClient client = WebClient.builder()
                .baseUrl(OAUTH_PATH)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        AuthResponseDto block = client
                .post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("grant_type", "account_credentials")
                        .queryParam("account_id", ACCOUNT_ID)
                        .build())
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(AuthResponseDto.class)
                .block();
        return block.getAccessToken();
    }

    private static String buildToken() {
        String data = String.format("%s:%s", CLIENT_ID, CLIENT_SECRET);
        String token = Base64.getEncoder().encodeToString(data.getBytes());
        return String.format("Basic %s", token);
    }

    private static CreateMeetingRequestDto buildRequestDto(String name, LocalDateTime startTime, String timezone) {
        CreateMeetingRequestDto body = new CreateMeetingRequestDto(
                name,
                startTime,
                timezone,
                new MeetingSettings(
                        true,
                        false,
                        true,
                        true,
                        0,
                        2,
                        "voip",
                        "local",
                        false,
                        false
                )
        );
        return body;
    }


    private static String createMeeting(
            String accessToken,
            CreateMeetingRequestDto body
    ) {
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);

        WebClient client = WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        CreateMeetingResponseDto createMeetingResponse = client
                .post()
                .uri("/users/me/meetings")
                .body(BodyInserters.fromValue(body))
                .header("Authorization", String.format("Bearer %s", accessToken))
                .retrieve()
                .bodyToMono(CreateMeetingResponseDto.class)
                .block();
        return createMeetingResponse.getJoinUrl();
    }
}