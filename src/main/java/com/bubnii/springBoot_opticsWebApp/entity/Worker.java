package com.bubnii.springBoot_opticsWebApp.entity;

import lombok.*;


@Data
@EqualsAndHashCode(exclude = {"age", "email", "firstName", "lastName", "photo", "description"})
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int id;
    private int age;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String photo;
    private String description;
}
