package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    public String login() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", "{yourClientId}");
        requestBody.add("client_secret", "{yourClientSecret}");
        requestBody.add("username", "");
        requestBody.add("password", "");

        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForObject(
                "https://{yourDomain}/oauth/token",
                formEntity, String.class);
    }

    public ResponseEntity<String> getUserInfo(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        HttpEntity<String> formEntity = new HttpEntity<>("", headers);

        return restTemplate.exchange("https://dev-i3x2srsmxtyl8q7p.us.auth0.com/userinfo", HttpMethod.GET, formEntity,
                String.class);
    }
}
