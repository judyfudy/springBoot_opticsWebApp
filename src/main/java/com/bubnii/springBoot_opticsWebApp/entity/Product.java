package com.bubnii.springBoot_opticsWebApp.entity;

import lombok.*;


@Data
@EqualsAndHashCode(exclude = {"price", "quantity", "photo", "description", })
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private int price;
    private int quantity;
    private int productTypeId;
    private String name;
    private String description;
    private String photo;
    private String brand;
    private String model;
}
