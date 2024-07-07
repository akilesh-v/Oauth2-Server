package com.akilesh.oauth_server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akilesh.oauth_server.entiry.Users;
import com.akilesh.oauth_server.repository.UsersRepository;

/**
 * @author AkileshVasudevan
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(),
                user.getPassword(),
                getAuthorization(List.of("ROLE_"+user.getRole())));
    }

    private Collection<? extends GrantedAuthority> getAuthorization(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String roleName : roles) {
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        return authorities;
    }
}
