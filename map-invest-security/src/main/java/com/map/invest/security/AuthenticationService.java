package com.map.invest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.invest.dto.JwtResponseDTO;
import com.map.invest.entity.Acesso;
import com.map.invest.repository.AcessoRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AcessoRepositorio acessoRepositorio;

    public JwtResponseDTO refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            Acesso user = acessoRepositorio.buscarAcessoPorLogin(username);
            if (jwtService.isTokenValid(refreshToken, user.getLogin())) {
                var accessToken = jwtService.generateToken(user.getLogin());
                var authResponse = JwtResponseDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                return authResponse;
            }
        }
        return null;
    }
}
