package pl.mdados.ehealth.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.mdados.ehealth.model.EmotionReadout;
import pl.mdados.ehealth.model.PulseReadout;
import pl.mdados.ehealth.repository.PulseRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BasicPulseService implements PulseService {
    private final PulseRepository pulseRepository;

    public BasicPulseService(PulseRepository pulseRepository) {
        this.pulseRepository = pulseRepository;
    }

    @Override
    public Flux<PulseReadout> getPulsesByUserId(String id, Pageable pageable) {
        return pulseRepository.findAllByUserId(id, pageable);
    }

    @Override
    public Mono<PulseReadout> save(PulseReadout pulseReadout) {
        return pulseRepository.save(pulseReadout);
    }

    @Override
    public Mono<PulseReadout> addComment(String pulseId, String comment) {
        return pulseRepository
                .findById(pulseId)
                .flatMap(pulseReadout -> {
                    pulseReadout.setComment(comment);
                    return pulseRepository.save(pulseReadout);
                });
    }
}
