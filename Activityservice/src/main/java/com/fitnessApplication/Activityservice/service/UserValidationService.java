package com.fitnessApplication.Activityservice.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final WebClient userServiceWebClient;
    private static final Logger log =
            LoggerFactory.getLogger(UserValidationService.class);

    public boolean validateUser(String id) {

        log.info("Calling User Service for {}", id);

        try {
            Boolean response = userServiceWebClient.get()
                    .uri("/api/users/{id}/validate", id)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            return Boolean.TRUE.equals(response);

        } catch (WebClientResponseException.NotFound e) {
            return false;

        } catch (Exception e) {
            log.error("Failed to validate user: {}", id, e);
            return false;
        }
    }
}
