package com.bubnii.springBoot_opticsWebApp.dto;

import com.bubnii.springBoot_opticsWebApp.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"firstName", "lastName", "email", "userType"})
public class UserDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
}
