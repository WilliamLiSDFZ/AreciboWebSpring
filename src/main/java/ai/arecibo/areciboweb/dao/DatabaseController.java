package ai.arecibo.areciboweb.dao;

import java.time.LocalDateTime;

public interface DatabaseController {
    boolean insertMessageToContact(String name, String email, String message, LocalDateTime time);
}
