package com.bubnii.springBoot_opticsWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"firstName", "lastName", "email"})
public class UserDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
