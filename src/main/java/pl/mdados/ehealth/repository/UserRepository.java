package pl.mdados.ehealth.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pl.mdados.ehealth.model.User;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByName(String name);
    Mono<Void> deleteById(String id);
}
