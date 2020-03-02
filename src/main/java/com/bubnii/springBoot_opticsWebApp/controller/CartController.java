package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.ProductDTO;
import com.bubnii.springBoot_opticsWebApp.security.jwt.JwtProvider;
import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public List<ProductDTO> getAllProductsInCart(Authentication authentication) {
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        return productService.getAllProductsInCart(principal.getId());
    }

    @PostMapping
    public ResponseEntity<?> addProductToCart(@RequestParam("productId") final String productId, Authentication authentication) {
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        productService.addProductToCart(principal.getId(), Integer.parseInt(productId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromCart(@RequestParam("productId") final String productId, Authentication authentication) {
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        productService.deleteFromCart(Integer.parseInt(productId), principal.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(Authentication authentication) {
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        productService.clearCart(principal.getId());
        return ResponseEntity.ok().build();
    }
}
