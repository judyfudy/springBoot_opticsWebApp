package com.bubnii.springBoot_opticsWebApp.service.implementation;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.UserRepository;
import com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder, final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UserDTO> getAll() {
        return userRepository.getAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void add(final User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        userRepository.add(user);
    }

    @Override
    public UserDTO get(final int id) {
        return modelMapper.map(userRepository.get(id), UserDTO.class);
    }

    @Override
    public boolean update(final User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean delete(final int id) {
        return userRepository.delete(id);
    }

    @Override
    public UserDTO getPersonByCredentials(final String username) {
        return modelMapper.map(userRepository.getPersonByCredentials(username), UserDTO.class);
    }

}
