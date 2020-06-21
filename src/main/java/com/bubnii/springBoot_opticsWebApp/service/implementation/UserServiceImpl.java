package com.bubnii.springBoot_opticsWebApp.service.implementation;

import com.bubnii.springBoot_opticsWebApp.dto.UserDTO;
import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.repository.interfaces.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements com.bubnii.springBoot_opticsWebApp.service.interfaces.UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder, final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
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
    public void update(final User user) {
         userRepository.update(user);
    }

    @Override
    public void changeUserRole(final String role, final int id) {
        userRepository.changeUserRole(role, id);
    }

    @Override
    public void delete(final int id) {
        userRepository.delete(id);
    }

    @Override
    public UserDTO getPersonByCredentials(final String username) {
        return modelMapper.map(userRepository.getPersonByCredentials(username), UserDTO.class);
    }

}
