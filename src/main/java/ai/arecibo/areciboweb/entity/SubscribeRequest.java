package ai.arecibo.areciboweb.entity;

import java.util.Objects;

public class SubscribeRequest {
    String email;

    String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeRequest that = (SubscribeRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "email='" + email + '\'' +
                ", honeypot_field='" + name + '\'' +
                '}';
    }
}
