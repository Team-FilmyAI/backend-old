package com.filmyai.backend.service.SecurityService;

import com.filmyai.backend.model.Users;
import com.filmyai.backend.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findUsersByEmail(email);
        return new CustomeUserDetails(user.orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email)));
    }
}
