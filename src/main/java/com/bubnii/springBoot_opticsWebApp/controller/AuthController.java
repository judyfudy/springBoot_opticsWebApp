package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.security.dto.JwtResponse;
import com.bubnii.springBoot_opticsWebApp.security.dto.LoginForm;
import com.bubnii.springBoot_opticsWebApp.security.dto.MessageResponse;
import com.bubnii.springBoot_opticsWebApp.security.jwt.JwtProvider;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(final AuthenticationManager authenticationManager, final UserService userService, final JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/authenticate")
    @PreAuthorize("permitAll()")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody final LoginForm loginRequest) {
        return authenticate(loginRequest);
    }

    @PostMapping("/signup")
    @PreAuthorize("permitAll()")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody final User user) {
        userService.add(user);
        return ResponseEntity.ok(new MessageResponse("User has been successfully registered: " + user.getEmail()));
    }

    private ResponseEntity<JwtResponse> authenticate(final LoginForm loginRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JwtResponse(
                jwtProvider.generateAccessToken(authentication),
                jwtProvider.generateRefreshToken(authentication)
        ));
    }
}