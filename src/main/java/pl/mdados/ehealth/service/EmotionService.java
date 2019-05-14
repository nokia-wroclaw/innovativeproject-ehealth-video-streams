package pl.mdados.ehealth.service;

import org.springframework.data.domain.Pageable;
import pl.mdados.ehealth.model.EmotionReadout;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmotionService {
    Flux<EmotionReadout> getEmotionsByUserId(String id, Pageable pageable);
    Flux<EmotionReadout> getEmotionsByUserId(String id);
    Mono<EmotionReadout> save(EmotionReadout emotionReadout);
    Mono<EmotionReadout> addComment(String emotionId, String comment);
}
