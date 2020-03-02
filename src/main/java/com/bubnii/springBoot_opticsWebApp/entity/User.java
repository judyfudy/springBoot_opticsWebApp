package com.bubnii.springBoot_opticsWebApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(exclude = {"pass", "firstName", "lastName", "email"})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String pass;
    private String firstName;
    private String lastName;
    private String email;
}
