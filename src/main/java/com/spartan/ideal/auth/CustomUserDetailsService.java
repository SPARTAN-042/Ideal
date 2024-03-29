package com.spartan.ideal.auth;

import com.spartan.ideal.model.User;
import com.spartan.ideal.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


// An implementation of UserDetailServices
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User userObj = user.get();
            Collection<GrantedAuthority> authorities = Arrays.stream(userObj.getRole().split(","))
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    userObj.getUsername(),
                    userObj.getPassword(),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }
}
