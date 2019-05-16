package joboonja.DTO;

public class LoginResponseDTO {
    private String id;
    private String token;

    public LoginResponseDTO() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
