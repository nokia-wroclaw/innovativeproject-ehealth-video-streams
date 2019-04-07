package pl.mdados.ehealth.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mdados.ehealth.model.User;
import reactor.core.publisher.Mono;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadResponse {
    private String id;
    private String name;
    private Set<String> roles;

    public static Mono<UserReadResponse> fromUser(Mono<User> user) {
        return user.map(u -> new UserReadResponse(u.getId(), u.getName(), u.getRoles()));
    }

    public static UserReadResponse fromUser(User user) {

        return new UserReadResponse(user.getId(), user.getName(), user.getRoles());
    }
}
