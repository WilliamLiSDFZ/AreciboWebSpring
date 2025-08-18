package ai.arecibo.areciboweb.service;

import ai.arecibo.areciboweb.entity.SubscribeResult;

public interface MainService {

    boolean addMessage(String name, String email, String message);

    SubscribeResult addSubscribe(String email);
}
