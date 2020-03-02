package com.bubnii.springBoot_opticsWebApp.security.services;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.UserRepository;
import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    public MyAuthenticationProvider(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            throw new BadCredentialsException("Cannot authentication an empty authentication.");
        }

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();


        final User user = userRepository.getPersonByCredentials(username);

        return new UsernamePasswordAuthenticationToken(UserPrinciple.build(user), password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
