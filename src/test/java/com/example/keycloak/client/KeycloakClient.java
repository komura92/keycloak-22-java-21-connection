package com.example.keycloak.client;

import java.net.URI;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class KeycloakClient {

    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class TokenResponse {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("session_state")
        private String sessionState;

        @JsonProperty("scope")
        private String scope;

        @JsonProperty("expires_in")
        private int expiresIn;

        @JsonProperty("refresh_expires_in")
        private int refreshExpiresIn;
    }


    public String getAccessToken(String user, String password) {
        TokenResponse tokenResponse = getTokenResponse(user, password);
        return tokenResponse.accessToken;
    }

    private TokenResponse getTokenResponse(String user, String password) {
        return WebClient.builder()
                .build()
                .post()
                .uri(URI.create("http://localhost:8080/realms/custom-realm/protocol/openid-connect/token"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(getLoginBody(user, password))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();
    }

    private BodyInserters.FormInserter<String> getLoginBody(String user, String password) {
        return BodyInserters.fromFormData("username", user)
                .with("password", password)
                .with("grant_type", "password")
                .with("client_id", "custom-client");
    }
}
