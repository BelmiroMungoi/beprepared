package com.bbm.beprepared.config;

import com.bbm.beprepared.repository.CitizenRepository;
import com.bbm.beprepared.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig implements UserDetailsService {

    private final UserRepository userRepository;
    private final CitizenRepository citizenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("@")) {
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        }
        return citizenRepository.findByPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cidadão não encontrado!"));
    }
}
