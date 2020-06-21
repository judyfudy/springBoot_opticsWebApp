package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.security.dto.FacebookUserData;
import com.bubnii.springBoot_opticsWebApp.security.dto.JwtResponse;
import com.bubnii.springBoot_opticsWebApp.security.dto.LoginForm;
import com.bubnii.springBoot_opticsWebApp.security.dto.MessageResponse;
import com.bubnii.springBoot_opticsWebApp.security.jwt.JwtProvider;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.FacebookService;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final FacebookService facebookService;

    public AuthController(final AuthenticationManager authenticationManager, final UserService userService,
                          final JwtProvider jwtProvider, final FacebookService facebookService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.facebookService = facebookService;
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

    @PostMapping("/signinFacebook")
    @PreAuthorize("permitAll()")
    public ResponseEntity<JwtResponse> authenticateFacebookUser(@Valid @RequestBody FacebookUserData userData) {
        LoginForm form = facebookService.authenticateUser(userData);
        return authenticate(form);
    }

    @GetMapping("/signup")
    @PreAuthorize("permitAll()")
    public boolean checkUniqueUsername(@RequestParam("name") final String username) {
        List<UserDTO> usersList = userService.getAll();

        for (UserDTO iterator : usersList) {
            if (iterator.getUsername().contains(username)) {
                return true; // if true - user exists
            }
        }
        return false; // if false - user doesn't exists
    }

    private ResponseEntity<JwtResponse> authenticate(final LoginForm loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JwtResponse(
                jwtProvider.generateAccessToken(authentication),
                jwtProvider.generateRefreshToken(authentication)
        ));
    }
}