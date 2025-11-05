package com.larbi.aitelhadj.spring_boot_3_security_jwt.service;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.User;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

/**
 * Service to load user-specific data for Spring Security authentication.
 * Implements {@link UserDetailsService} and loads a {@link UserDetails}
 * object based on the username from the database.
 *
 * @author larbi.aitelhadj
 */
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads a user by their username.
     *
     * @param username the username of the user to load
     * @return a {@link UserDetails} object containing the user's credentials and authorities
     * @throws UsernameNotFoundException if no user with the given username exists
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found!"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

}
