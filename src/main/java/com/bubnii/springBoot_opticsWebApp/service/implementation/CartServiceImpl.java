package com.bubnii.springBoot_opticsWebApp.service.implementation;

import com.bubnii.springBoot_opticsWebApp.dto.ProductDTO;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.CartService;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.bubnii.springBoot_opticsWebApp.utils.EmailSender.sendEmail;

@Service
public class CartServiceImpl implements CartService {
    private final ProductService productService;

    public CartServiceImpl(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void sendMail(final String email, final int userId) {
        sendEmail(email, buildOrderMessage(userId));
        productService.clearCart(userId);
    }

    private String buildOrderMessage(final int userId) {
        final List<ProductDTO> productList = productService.getAllProductsInCart(userId);

        return String.valueOf(getGeneralOrderInfo(productList, userId));
    }

    private StringBuilder getGeneralOrderInfo(final List<ProductDTO> productList, final int userId) {
        final StringBuilder orderMessage = new StringBuilder();

        orderMessage
                .append("№ замовлення: ")
                .append(randomizeNumberOfOrder()).append("\n")
                .append("Дата замовлення: ")
                .append(new Date()).append("\n")
                .append("Реквізити оплати: 5375 4141 0550 2925\n");

        setProductInfoInOrderMessage(productList, orderMessage);

        orderMessage
                .append("Загальна сума до оплати: ")
                .append(getTotalPrice(userId))
                .append(" грн.");

        return orderMessage;
    }

    private void setProductInfoInOrderMessage(final List<ProductDTO> productList, final StringBuilder orderMessage) {
        final List<String> productsInfo = new LinkedList<>();

        getGeneralProductsInfo(productList, productsInfo);

        setGeneralProductInfoIntoOrderMessage(productsInfo, orderMessage);
    }

    private long randomizeNumberOfOrder() {
        return (long) (10000 + Math.random() * 10000);
    }

    private int getTotalPrice(final int userId) {
        return productService.getAllProductsInCart(userId).stream().mapToInt(ProductDTO::getPrice).sum();
    }

    private void getGeneralProductsInfo(final List<ProductDTO> productList, final List<String> productsInfo) {
        productList.forEach(x -> productsInfo.add("Назва: " + x.getName() + " | Модель: " + x.getModel()
                + " | Ціна: " + x.getPrice()));
    }

    private void setGeneralProductInfoIntoOrderMessage(final List<String> productsInfo, final StringBuilder orderMessage) {
        productsInfo.forEach(x -> orderMessage.append(x).append("\n"));
    }

}
