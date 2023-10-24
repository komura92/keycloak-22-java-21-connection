package com.example.keycloak.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.keycloak.client.KeycloakClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ExampleControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    private final KeycloakClient keycloakClient = new KeycloakClient();

    @Test
    void unsecuredGetReturnsProperResponse() throws Exception {
        mvc.perform(get("/example/unsecured"))
                .andExpect(content().string("Unsecured content"));
    }

    @Test
    void securedGetWithoutTokenThrowsUnauthorized() throws Exception {
        mvc.perform(get("/example/secured"))
                .andExpect(status().is(401));
    }

    @Test
    void securedGetWithProperAccessTokenReturnsProperResponse() throws Exception {
        String token = keycloakClient.getAccessToken("user", "user");
        mvc.perform(authorizedGet("/example/secured", token))
                .andExpect(content().string("Secured content"));
    }

    @Test
    void securedGetWithInvalidAccessTokenThrowsUnauthorized() throws Exception {
        mvc.perform(authorizedGet("/example/secured", "fake token"))
                .andExpect(status().is(401));
    }

    private RequestBuilder authorizedGet(String path, String token) {
        return MockMvcRequestBuilders
                .get(path)
                .header("Authorization", "Bearer " + token);
    }
}