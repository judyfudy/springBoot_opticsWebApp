package com.bubnii.springBoot_opticsWebApp.controller;


import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.security.jwt.JwtProvider;
import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getAuthenticatedUserInfo(Authentication authentication) {
        UserPrinciple principle = (UserPrinciple) authentication.getPrincipal();
        return userService.get(principle.getId());
    }

}
