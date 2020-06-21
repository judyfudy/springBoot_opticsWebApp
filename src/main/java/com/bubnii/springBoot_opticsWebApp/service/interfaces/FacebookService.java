package com.bubnii.springBoot_opticsWebApp.service.interfaces;

import com.bubnii.springBoot_opticsWebApp.security.dto.FacebookData;
import com.bubnii.springBoot_opticsWebApp.security.dto.FacebookUserData;
import com.bubnii.springBoot_opticsWebApp.security.dto.LoginForm;

public interface FacebookService {

    FacebookData verifyFacebookToken(String accessToken);
    LoginForm authenticateUser(FacebookUserData facebookUserData) throws IllegalArgumentException;
}
