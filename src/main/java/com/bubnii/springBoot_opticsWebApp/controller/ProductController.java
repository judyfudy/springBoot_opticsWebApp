package com.bubnii.springBoot_opticsWebApp.controller;

import com.bubnii.springBoot_opticsWebApp.dto.ProductDTO;
import com.bubnii.springBoot_opticsWebApp.security.dto.MessageResponse;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all/{typeId}")
    public List<ProductDTO> getProductsByType(@PathVariable final int typeId) {
        return productService.getProductsByType(typeId);
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteProduct(@RequestParam("productId") final int productId) {
        productService.delete(productId);
        return ResponseEntity.ok(new MessageResponse("Product " + productId + "successfully deleted"));
    }
}
