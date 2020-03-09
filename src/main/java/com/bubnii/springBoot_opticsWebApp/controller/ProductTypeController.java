package com.bubnii.springBoot_opticsWebApp.controller;


import com.bubnii.springBoot_opticsWebApp.dto.ProductTypeDTO;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    private final ProductService productService;

    public ProductTypeController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public List<ProductTypeDTO> productTypeList() {
        return productService.getAllTypesOfProducts();
    }
}
