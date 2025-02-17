package org.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.web.controller.AuthController;
import org.web.dto.login.LoginRequest;
import org.web.dto.login.LoginResponse;
import org.web.security.JwtUtil;


import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        jwtUtil = mock(JwtUtil.class);
        authController = new AuthController(authenticationManager, jwtUtil);
    }

    @Test
    void testLoginSuccess() {
        // Given
        LoginRequest loginRequest = new LoginRequest("testUser", "password123");

        User userDetails = new User("testUser", "password123", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtUtil.generateRs256Token(userDetails)).thenReturn("mocked-jwt-token");

        // When
        ResponseEntity<LoginResponse> response = authController.login(loginRequest);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("mocked-jwt-token", response.getBody().getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil, times(1)).generateRs256Token(userDetails);
    }


}