package joboonja.DTO;

public class LoginResponseDTO {
    private int id;
    private String token;

    public LoginResponseDTO() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
