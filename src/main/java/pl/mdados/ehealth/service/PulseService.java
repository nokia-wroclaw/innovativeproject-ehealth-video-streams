package pl.mdados.ehealth.service;

import org.springframework.data.domain.Pageable;
import pl.mdados.ehealth.model.PulseReadout;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PulseService {
    Flux<PulseReadout> getPulsesByUserId(String id, Pageable pageable);
    Mono<PulseReadout> save(PulseReadout pulseReadout);
    Mono<PulseReadout> addComment(String pulseId, String comment);
}
