package com.bubnii.springBoot_opticsWebApp.service.implementation;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.security.dto.FacebookData;
import com.bubnii.springBoot_opticsWebApp.security.dto.FacebookUserData;
import com.bubnii.springBoot_opticsWebApp.security.dto.LoginForm;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.FacebookService;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FacebookServiceImpl implements FacebookService {

    private UserService userService;
    private final PasswordEncoder encoder;

    @Override
    public FacebookData verifyFacebookToken(String accessToken) {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"email", "first_name", "id", "last_name", "name"};
        return facebook.fetchObject("me", FacebookData.class, fields);
    }

    @Override
    public LoginForm authenticateUser(FacebookUserData facebookUserData) throws IllegalArgumentException {

        FacebookData facebookData = verifyFacebookToken(facebookUserData.getAuthToken());
        LoginForm form = new LoginForm();

        if(!userService.existsByUsername(facebookData.getName())) {
            User addUser = facebookData.toUser();
            addUser.setPass(encoder.encode(facebookData.getId()));
            userService.add(addUser);
            System.out.println("in service " + addUser);
            form.setUsername(facebookData.getName());
            form.setPassword(facebookData.getId());
        } else {
            UserDTO foundUser = userService.getPersonByCredentials(facebookData.getName());
            form.setUsername(foundUser.getUsername());
            form.setPassword(facebookData.getId());
        }

        return form;
    }
}
