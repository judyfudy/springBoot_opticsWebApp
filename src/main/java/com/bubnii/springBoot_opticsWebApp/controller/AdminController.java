package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.Product;
import com.bubnii.springBoot_opticsWebApp.security.dto.MessageResponse;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {
    private final UserService userService;
    private final ProductService productService;

    public AdminController(final UserService userService, final ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> userList() {
        return userService.getAll();
    }

    @PutMapping("/admin")
    public ResponseEntity<MessageResponse> changeUserRole(@RequestParam("role") final String role, @RequestParam("id") final int id) {
        userService.changeUserRole(role, id);
        return ResponseEntity.ok(new MessageResponse("User role successfully changed"));
    }

    @PostMapping("/admin")
    public ResponseEntity<MessageResponse> addNewProduct(@RequestBody final Product product) {
        productService.add(product);
        return ResponseEntity.ok(new MessageResponse("Product successfully added"));
    }
}
