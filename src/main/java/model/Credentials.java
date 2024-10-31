package model;

import lombok.Getter;
import lombok.Setter;

public class Credentials {

@Getter
@Setter
private String email;
    @Getter @Setter
    private String password;
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Credentials() {
    }

}
