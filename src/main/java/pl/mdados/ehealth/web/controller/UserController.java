package pl.mdados.ehealth.web.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.mdados.ehealth.model.EmotionReadout;
import pl.mdados.ehealth.model.PulseReadout;
import pl.mdados.ehealth.security.UserCredentials;
import pl.mdados.ehealth.service.EmotionService;
import pl.mdados.ehealth.service.PulseService;
import pl.mdados.ehealth.service.UserService;
import pl.mdados.ehealth.web.request.AddCommentRequest;
import pl.mdados.ehealth.web.request.EmotionWriteRequest;
import pl.mdados.ehealth.web.request.PulseWriteRequest;
import pl.mdados.ehealth.web.request.UserCreateRequest;
import pl.mdados.ehealth.web.response.UserReadResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final PulseService pulseService;
    private final EmotionService emotionService;

    public UserController(UserService userService, PulseService pulseService, EmotionService emotionService) {
        this.userService = userService;
        this.pulseService = pulseService;
        this.emotionService = emotionService;
    }

    @GetMapping
    public Flux<UserReadResponse> getAll(Authentication authentication) {
        UserCredentials userCredentials = (UserCredentials)authentication.getPrincipal();
        System.out.println(userCredentials.getUserId());
        return userService.findAll().map(UserReadResponse::fromUser);
    }
    @GetMapping("/{id}")
    public Mono<UserReadResponse> getById(@PathVariable String id) {
        return UserReadResponse.fromUser(userService.findById(id));
    }

    @PostMapping
    public Mono<UserReadResponse> save(@RequestBody UserCreateRequest user) {

        return UserReadResponse.fromUser(userService.save(user.toUser()));
    }

    @GetMapping("/{id}/emotions")
    public Flux<EmotionReadout> getUserEmotions(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "desc") String direction
            ) {
        return emotionService.getEmotionsByUserId(id, PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(direction.toUpperCase()), sort)));
    }

    @GetMapping("/{id}/pulses")
    public Flux<PulseReadout> getUserPulses(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return pulseService.getPulsesByUserId(
                id,
                PageRequest.of(
                        page,
                        size,
                        Sort.by(Sort.Direction.fromString(direction.toUpperCase()), sort)));
    }

    @PostMapping("/{id}/pulses")
    public Mono<PulseReadout> saveUserPulse(@PathVariable String id, @RequestBody PulseWriteRequest pulseWriteRequest) {
        PulseReadout pulseReadout = new PulseReadout();
        pulseReadout.setUserId(id);
        pulseReadout.setPulse(pulseWriteRequest.getPulse());
        return pulseService.save(pulseReadout);
    }

    @PostMapping("/{id}/emotions")
    public Mono<EmotionReadout> saveUserEmotion(@PathVariable String id, @RequestBody EmotionWriteRequest emotionWriteRequest) {
        EmotionReadout emotionReadout = new EmotionReadout();
        emotionReadout.setAnger(emotionWriteRequest.getAnger());
        emotionReadout.setDisgust(emotionWriteRequest.getDisgust());
        emotionReadout.setFear(emotionWriteRequest.getFear());
        emotionReadout.setHappiness(emotionWriteRequest.getHappiness());
        emotionReadout.setSadness(emotionWriteRequest.getSadness());
        emotionReadout.setSurprise(emotionWriteRequest.getSurprise());
        emotionReadout.setUserId(id);
        return emotionService.save(emotionReadout);
    }

    @PostMapping("/{id}/pulses/{pulseId}/comments")
    public Mono<PulseReadout> addPulseComment(
            @PathVariable String id,
            @PathVariable String pulseId,
            @RequestBody AddCommentRequest addCommentRequest
            ) {
        return pulseService.addComment(pulseId, addCommentRequest.getComment());
    }

    @PostMapping("/{id}/emotions/{emotionId}/comments")
    public Mono<EmotionReadout> addEmotionComment(
            @PathVariable String id,
            @PathVariable String emotionId,
            @RequestBody AddCommentRequest addCommentRequest
    ) {
        return emotionService.addComment(emotionId,addCommentRequest.getComment());
    }
}
