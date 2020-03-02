package com.bubnii.springBoot_opticsWebApp.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginForm {
    @NotBlank(message = "The username field can not be empty")
    @Size(min = 3, max = 32,
            message = "The username field should be between 3 and 32 characters")
    private String username;

    @NotBlank(message = "The password field can not be empty")
    @Size(min = 3, max = 16,
            message = "The password field should be between 6 and 16 characters")
    private String password;

    public LoginForm(@NotBlank(message = "The username field can not be empty") @Size(min = 3, max = 32,
            message = "The username field should be between 3 and 32 characters") String username,
                     @NotBlank(message = "The password field can not be empty") @Size(min = 3, max = 16,
            message = "The password field should be between 6 and 16 characters") String password) {
        this.username = username;
        this.password = password;
    }
}
