package pl.mdados.ehealth.web.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mdados.ehealth.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    private String name;
    private String password;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return user;
    }
}
