package com.bubnii.springBoot_opticsWebApp.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

    @NotBlank(message = "The firstname field can not be empty")
    @Size(min = 3, max = 16,
            message = "The firstname field should be between 3 and 16 characters")
    private String firstName;

    @NotBlank(message = "The lastname field can not be empty")
    @Size(min = 3, max = 16,
            message = "The lastname field should be between 3 and 16 characters")
    private String lastName;

    @NotBlank(message = "The username field can not be empty")
    @Size(min = 3, max = 32,
            message = "The username field should be between 3 and 32 characters")
    private String username;

    @NotBlank(message = "The password field can not be empty")
    @Size(min = 3, max = 16,
            message = "The password field should be between 3 and 16 characters")
    @Size(min = 6, max = 16)
    private String password;

    @NotBlank(message = "The email field can not be empty")
    @Email(message = "The email is invalid")
    @Size(min = 3, max = 60,
            message = "The email field should be between 3 and 60 characters")
    private String email;

}
