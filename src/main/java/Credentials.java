import model.User;

public class Credentials {
    private String login;
    private String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Credentials fromCourier(User user) {
        return new Credentials(user.getName(), user.getPassword());
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }
}
