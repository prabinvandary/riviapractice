package com.example.demo.security;


import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID=5253587896458L;
    Logger logger= LoggerFactory.getLogger(this.getClass());
    private List<GrantedAuthority> authority=new ArrayList<>();
    private User user;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authority.add(new SimpleGrantedAuthority(user.getUserType().toString()));
        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

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
}