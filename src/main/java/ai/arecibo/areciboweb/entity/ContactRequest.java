package ai.arecibo.areciboweb.entity;

import java.util.Objects;

public class ContactRequest {
    private String name;
    private String email;
    private String message;
    private String token;

    public ContactRequest() {
    }

    public ContactRequest(String name, String email, String message, String token) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContactRequest that = (ContactRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(message, that.message) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, message, token);
    }

    @Override
    public String toString() {
        return "ContactRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
