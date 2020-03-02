package com.bubnii.springBoot_opticsWebApp.service.interfaces;

import com.bubnii.springBoot_opticsWebApp.dto.ProductDTO;
import com.bubnii.springBoot_opticsWebApp.dto.ProductTypeDTO;
import com.bubnii.springBoot_opticsWebApp.entity.Product;
import com.bubnii.springBoot_opticsWebApp.entity.ProductType;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    void addProductToCart(final int personId, final int productId);

    List<ProductDTO> getProductsByType(final int typeId);

    List<ProductTypeDTO> getAllTypesOfProducts();

    List<ProductDTO> getAllProductsInCart(final int userId);

    void deleteFromCart(final int productId, final int userId);

    void clearCart(final int userId);
}
