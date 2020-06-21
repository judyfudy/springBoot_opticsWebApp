package com.bubnii.springBoot_opticsWebApp.entity;

import com.bubnii.springBoot_opticsWebApp.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(exclude = {"pass", "firstName", "lastName", "email", "userType"})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String pass;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
}
