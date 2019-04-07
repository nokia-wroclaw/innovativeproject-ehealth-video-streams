package pl.mdados.ehealth.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.mdados.ehealth.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Flux<User> findAll();


    Mono<User> findById(String id);

    Mono<User> findByName(String name);

    Mono<User> save(User user);

    Mono<Void> deleteById(String id);
}
