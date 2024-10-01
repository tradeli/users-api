package com.viafoura.users_api.adapters.outgoing.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.viafoura.users_api.core.ports.outgoing.AvatarProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AvatarService implements AvatarProvider {

    private final RestClient restClient;
    private final String baseUrl;

    public AvatarService(RestClient restClient, @Value("${reqres.api.user-url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    @Cacheable("avatars")
    @Override
    public String getAvatar() throws AvatarServiceException {
        //TODO: left hardcoded because the Mock API dosen't seem to support a generic ID (like our email) as key
        String apiUrl = baseUrl + 1;

        try {
            ResponseEntity<JsonNode> response = restClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .toEntity(JsonNode.class);

            JsonNode root = response.getBody();
            if (root != null) {
                return root.path("data").path("avatar").asText();
            } else {
                throw new RuntimeException("Failed to parse API response");
            }
        } catch (Exception e) {
            throw new AvatarServiceException(e.getMessage(), e);
        }
    }
}
