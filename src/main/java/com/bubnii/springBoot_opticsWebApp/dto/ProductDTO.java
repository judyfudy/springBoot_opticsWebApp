package com.bubnii.springBoot_opticsWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"price", "quantity", "photo", "description", })
public class ProductDTO {
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
