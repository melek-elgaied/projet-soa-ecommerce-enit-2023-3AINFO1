package com.enit.Service;

import java.util.HashMap;
import java.util.Map;

import com.enit.api.Token.TokenResponse;
public class TokenManager {

    private Map<String, String> userCredentials;
    private Map<String, String> userTokens;

    public TokenManager() {
        // Initialize user credentials and tokens (for demonstration purposes)
        userCredentials = new HashMap<>();
        userCredentials.put("user1@example.com", "password1");
        userCredentials.put("user2@example.com", "password2");

        userTokens = new HashMap<>();
    }

    public TokenResponse getAccessToken(String email, String password) {
        // Check if the provided credentials are valid
        if (isValidCredentials(email, password)) {
            // Check if a token already exists for the user
            if (userTokens.containsKey(email)) {
                return createTokenResponse(userTokens.get(email));
            } else {
                // Generate a new token (for demonstration purposes, a simple concatenation is used)
                String token = email + "_token";
                userTokens.put(email, token);
                return createTokenResponse(token);
            }
        } else {
            // Invalid credentials, return null or throw an exception based on your design
            return null;
        }
    }

    private TokenResponse createTokenResponse(String accessToken) {
        // Create a new TokenResponse object with the provided access token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(accessToken);
        // Set other relevant properties based on your requirements
        tokenResponse.setExpiresIn(3600); // Example expiration time in seconds
        tokenResponse.setTokenType("Bearer"); // Example token type
        return tokenResponse;
    }

    private boolean isValidCredentials(String email, String password) {
        // Check if the provided credentials match the stored credentials
        return userCredentials.containsKey(email) && userCredentials.get(email).equals(password);
    }

    
}
