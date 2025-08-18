package ai.arecibo.areciboweb.entity;

import java.util.Objects;

public class SubscribeRequest {
    String email;

    public SubscribeRequest() {
    }

    public SubscribeRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeRequest that = (SubscribeRequest) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
