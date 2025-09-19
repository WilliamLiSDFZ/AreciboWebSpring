package ai.arecibo.areciboweb.service;

import ai.arecibo.areciboweb.dao.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PasscodeServiceImplement implements PasscodeService {

    @Autowired
    private DatabaseController databaseController;

    @Override
    public boolean validatePasscode(String passcode) {
        if (passcode == null || passcode.trim().isEmpty()) {
            return false;
        }
        return databaseController.validatePasscode(passcode.trim());
    }

    @Override
    public boolean updateLastLogin(String passcode) {
        if (passcode == null || passcode.trim().isEmpty()) {
            return false;
        }
        return databaseController.updateLastLogin(passcode.trim(), LocalDateTime.now());
    }

    @Override
    public List<Map<String, Object>> getPasscodes(int page, int size) {
        int offset = (page - 1) * size;
        return databaseController.getAllPasscodes(offset, size);
    }

    @Override
    public int getPasscodeCount() {
        return databaseController.getPasscodeCount();
    }

    @Override
    public boolean addPasscode(String passcode) {
        if (passcode == null || passcode.trim().isEmpty()) {
            return false;
        }
        return databaseController.addPasscode(passcode.trim(), LocalDateTime.now());
    }

    @Override
    public boolean deletePasscode(int passcodeId) {
        return databaseController.deletePasscode(passcodeId);
    }
}
