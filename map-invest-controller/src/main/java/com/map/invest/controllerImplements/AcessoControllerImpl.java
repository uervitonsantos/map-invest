package com.map.invest.controllerImplements;

import com.map.invest.dto.AuthRequestDTO;
import com.map.invest.dto.JwtResponseDTO;
import com.map.invest.dto.RefreshTokenRequestDTO;
import com.map.invest.resource.AcessoController;
import com.map.invest.security.AuthenticationService;
import com.map.invest.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api/mapinvest")
@RestController
public class AcessoControllerImpl implements AcessoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    @Override
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            var refreshToken = jwtService.generateRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/refreshToken")
    @Override
    public JwtResponseDTO AuthenticateAndGetrefreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return authenticationService.refreshToken(request, response);
    }
}
