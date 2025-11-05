package com.larbi.aitelhadj.spring_boot_3_security_jwt.service;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.User;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.repository.UserRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.Optional;

/**
 * Unit test for {@link CustomerUserDetailsService}.
 * <p>
 * Verifies that loading a user by username returns the expected {@link UserDetails}.
 *
 * @author larbi.aitelhadj
 */
@SpringBootTest
class CustomerUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomerUserDetailsService customerUserDetailsService;

    @Test
    void loadUserByUsername() {
        User user = new User(1L, "user1", "password1", "role_admin");
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername("user1");
        assertThat(userDetails).isNotNull();
        assertEquals("user1", userDetails.getUsername());
        assertEquals("password1", userDetails.getPassword());
        assertEquals(List.of("role_admin"), userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList());
    }
}