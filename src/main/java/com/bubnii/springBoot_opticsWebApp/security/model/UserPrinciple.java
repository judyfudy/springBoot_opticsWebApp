package com.bubnii.springBoot_opticsWebApp.security.model;

import com.bubnii.springBoot_opticsWebApp.entity.User;
import com.bubnii.springBoot_opticsWebApp.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrinciple implements UserDetails {
    private Integer id;
    private String username;
    private String email;
    private UserType userType;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() { return null; }

    public static UserPrinciple build(final User user) {
        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserType());
    }
}
