package com.bubnii.springBoot_opticsWebApp.service.implementation;

import com.bubnii.springBoot_opticsWebApp.dto.ProductDTO;
import com.bubnii.springBoot_opticsWebApp.dto.ProductTypeDTO;
import com.bubnii.springBoot_opticsWebApp.entity.Product;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.ProductRepository;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(final ProductRepository productRepository, final ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.getAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addProductToCart(final int personId, final int productId) {
        productRepository.addProductToCart(personId, productId);
    }

    @Override
    public List<ProductDTO> getProductsByType(final int typeId) {
        return productRepository.getProductsByType(typeId)
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductTypeDTO> getAllTypesOfProducts() {
        return productRepository.getAllTypesOfProducts()
                .stream()
                .map(productType -> modelMapper.map(productType, ProductTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsInCart(final int userId) {
        return productRepository.getAllProductsInCart(userId)
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFromCart(final int productId, final int userId) {
        productRepository.deleteFromCart(productId, userId);
    }

    public void clearCart(final int userId) {
        productRepository.clearCart(userId);
    }

    @Override
    public void add(Product product) {
        productRepository.add(product);
    }

    @Override
    public void delete(int productId) {
        productRepository.delete(productId);
    }
}
