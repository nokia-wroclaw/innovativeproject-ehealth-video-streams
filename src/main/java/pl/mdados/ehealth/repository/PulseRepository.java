package pl.mdados.ehealth.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pl.mdados.ehealth.model.PulseReadout;
import reactor.core.publisher.Flux;


@Repository
public interface PulseRepository extends ReactiveMongoRepository<PulseReadout, String> {
    Flux<PulseReadout> findAllByUserId(String userId, Pageable pageable);
    Flux<PulseReadout> findAllByUserId(String userId);
}
