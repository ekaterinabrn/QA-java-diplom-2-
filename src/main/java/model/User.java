package model;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String name;

    //конструктор с параметром
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    //конструктор без параметра
    public User() {
    }


}
