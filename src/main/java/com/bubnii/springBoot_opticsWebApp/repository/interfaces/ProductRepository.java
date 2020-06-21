package com.bubnii.springBoot_opticsWebApp.repository.interfaces;

import com.bubnii.springBoot_opticsWebApp.entity.Product;
import com.bubnii.springBoot_opticsWebApp.entity.ProductType;

import java.util.List;

public interface ProductRepository extends Repository<Product> {
    void addProductToCart(final int personId, final int productId);

    List<Product> getProductsByType(final int typeId);

    List<ProductType> getAllTypesOfProducts();

    List<Product> getAllProductsInCart(final int userId);

    void deleteFromCart(final int productId, final int userId);

    void clearCart(final int userId);

    void add(final Product product);

    void delete(final int productId);
}
