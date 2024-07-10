package io.bareun.sample.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtToken token = getToken(username);

        return JwtUserDetails.builder()
                .subject(token.getSubject())
                .scope(token.getScope())
                .build();
    }

    private JwtToken getToken(String username) {
        String[] names = username.split("/t");

        return JwtToken.builder()
                .subject(Long.valueOf(names[0]))
                .scope(JwtRole.valueOf(names[1]))
                .build();
    }
}
