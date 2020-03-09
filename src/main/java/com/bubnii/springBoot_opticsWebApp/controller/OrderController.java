package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;

    public OrderController(final CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<?> getOrderAndSendEmail(@AuthenticationPrincipal UserPrinciple principle) {
        cartService.sendMail(principle.getEmail(), principle.getId());
        return ResponseEntity.ok().build();
    }
}
