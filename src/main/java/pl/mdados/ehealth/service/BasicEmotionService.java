package pl.mdados.ehealth.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.mdados.ehealth.model.EmotionReadout;
import pl.mdados.ehealth.repository.EmotionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BasicEmotionService implements EmotionService {
    private final EmotionRepository emotionRepository;

    public BasicEmotionService(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    @Override
    public Flux<EmotionReadout> getEmotionsByUserId(String id, Pageable pageable) {
        return emotionRepository.findPageByUserId(id, pageable);
    }

    @Override
    public Flux<EmotionReadout> getEmotionsByUserId(String id) {
        return emotionRepository.findPageByUserId(id);
    }

    @Override
    public Mono<EmotionReadout> save(EmotionReadout emotionReadout) {
        return emotionRepository.save(emotionReadout);
    }

    @Override
    public Mono<EmotionReadout> addComment(String emotionId, String comment) {
        return emotionRepository.findById(emotionId)
                .flatMap(emotionReadout -> {
                    emotionReadout.setComment(comment);
                    return emotionRepository.save(emotionReadout);
                });
    }
}
