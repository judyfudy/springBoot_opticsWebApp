package com.bubnii.springBoot_opticsWebApp.security.dto;

import lombok.Data;

@Data
public class FacebookUserData {

    private String authToken;

    private String email;

    private String firstName;

    private String id;

    private String idToken;

    private String lastName;

    private String name;

    private String photoUrl;

    private String provider;

}
