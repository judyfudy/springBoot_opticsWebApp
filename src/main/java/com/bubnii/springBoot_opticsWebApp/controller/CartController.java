package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.ProductDTO;
import com.bubnii.springBoot_opticsWebApp.security.jwt.JwtProvider;
import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public List<ProductDTO> getAllProductsInCart(@AuthenticationPrincipal UserPrinciple principle) {
        return productService.getAllProductsInCart(principle.getId());
    }

    @PostMapping
    public ResponseEntity<?> addProductToCart(@RequestParam("productId") final String productId, @AuthenticationPrincipal UserPrinciple principle) {
        productService.addProductToCart(principle.getId(), Integer.parseInt(productId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFromCart(@RequestParam("productId") final String productId, @AuthenticationPrincipal UserPrinciple principle) {
        productService.deleteFromCart(Integer.parseInt(productId), principle.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal UserPrinciple principle) {
        productService.clearCart(principle.getId());
        return ResponseEntity.ok().build();
    }
}
