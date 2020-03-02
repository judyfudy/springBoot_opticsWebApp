package com.bubnii.springBoot_opticsWebApp.security.services;

import com.bubnii.springBoot_opticsWebApp.repository.interfaces.UserRepository;
import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return UserPrinciple.build(userRepository.getPersonByCredentials(username));
    }

    public UserDetails loadUserById(final Integer id) {
        return UserPrinciple.build(userRepository.get(id));
    }
}
