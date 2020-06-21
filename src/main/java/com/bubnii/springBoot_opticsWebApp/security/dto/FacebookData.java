package com.bubnii.springBoot_opticsWebApp.security.dto;

import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.enums.UserType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class FacebookData {
    private String email;
    private String first_name;
    private String id;
    private String last_name;
    private String name;

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPass(id);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setUsername(name);
        user.setUserType(UserType.USER);
        return user;
    }
}
