package pl.mdados.ehealth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pl.mdados.ehealth.service.UserService;

import java.util.stream.Collectors;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfiguration {

    private final UserService userService;

    public WebFluxSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                    .pathMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                    .anyExchange().authenticated()
                .and()
                    .httpBasic()
                .and()
                    .csrf().disable()
                .formLogin()
                    .disable()
                .build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return s -> userService.findByName(s)
                .map(u -> new UserCredentials(u.getName(),
                        u.getPassword(),
                        u.getRoles().stream().map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toSet()), u.getId()));
    }


}
