package com.bubnii.springBoot_opticsWebApp.repository.implementation;

import com.bubnii.springBoot_opticsWebApp.entity.Product;
import com.bubnii.springBoot_opticsWebApp.entity.ProductType;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.ProductRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProductToCart(final int personId, final int productId) {
        jdbcTemplate.update("INSERT INTO cart(user_id, product_id) VALUES(?,?)", personId, productId);
    }

    @Override
    public List<Product> getProductsByType(final int typeId) {
        return jdbcTemplate.query("SELECT * FROM product WHERE product_type_id=?",
                new BeanPropertyRowMapper<>(Product.class), typeId);
    }

    @Override
    public List<ProductType> getAllTypesOfProducts() {
        return jdbcTemplate.query("SELECT * FROM product_type", new BeanPropertyRowMapper<>(ProductType.class));
    }

    @Override
    public List<Product> getAllProductsInCart(final int userId) {
        return jdbcTemplate.query("SELECT * FROM product INNER JOIN cart ON id = product_id WHERE user_id=?",
                new BeanPropertyRowMapper<>(Product.class), userId);
    }

    @Override
    public void deleteFromCart(final int productId, final int userId) {
        jdbcTemplate.update("DELETE FROM cart WHERE product_id=? AND user_id=?", productId, userId);
    }

    @Override
    public void clearCart(final int userId) {
        jdbcTemplate.update("DELETE FROM cart WHERE user_id=?", userId);
    }

    @Override
    public List<Product> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final Product product) {
    }

    @Override
    public Product get(final int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(final Product product) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(final int id) {
        throw new UnsupportedOperationException();
    }
}
