package pl.mdados.ehealth.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.mdados.ehealth.model.EmotionReadout;
import reactor.core.publisher.Flux;


public interface EmotionRepository extends ReactiveMongoRepository<EmotionReadout, String> {
    Flux<EmotionReadout> findAllByUserId(String userId, Pageable pageable);
}
