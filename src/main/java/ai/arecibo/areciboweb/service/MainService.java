package ai.arecibo.areciboweb.service;

public interface MainService {

    boolean addMessage(String name, String email, String message);

    boolean addSubscribe(String email);
}
