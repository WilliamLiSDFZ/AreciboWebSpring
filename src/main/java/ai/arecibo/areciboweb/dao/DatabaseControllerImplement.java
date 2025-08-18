package ai.arecibo.areciboweb.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class DatabaseControllerImplement implements DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger;

    public DatabaseControllerImplement() {
        logger = LogManager.getLogger(DatabaseControllerImplement.class);
    }

    /**
     * Inserts a message into the contact database.
     *
     * @param name    the name of the contact person
     * @param email   the email address of the contact person
     * @param message the message content to be stored
     * @param time    the timestamp when the message was created
     * @return true if the message is successfully inserted, false otherwise
     */
    @Override
    public boolean insertMessageToContact(String name, String email, String message, LocalDateTime time) {
        try {
            jdbcTemplate.update("INSERT INTO contact (name, email, message, time) VALUES (?, ?, ?, ?)", name, email, message, time);
        }catch (Exception e) {
            logger.error("Error inserting message to database", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean addSubscribe(String email, LocalDateTime time) {
        try {
            jdbcTemplate.update("INSERT INTO subscribe (email, time) VALUES (?, ?)", email, time);
        } catch (Exception e) {
            logger.error("Error inserting subscribe to database", e);
            return false;
        }
        return true;
    }
}
