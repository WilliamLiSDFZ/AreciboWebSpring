package ai.arecibo.areciboweb.entity;

import java.time.LocalDateTime;

public class Passcode {
    private int passcodeId;
    private String passcode;
    private LocalDateTime time;
    private LocalDateTime lastLogin;

    public Passcode() {}

    public Passcode(String passcode, LocalDateTime time) {
        this.passcode = passcode;
        this.time = time;
    }

    public Passcode(int passcodeId, String passcode, LocalDateTime time, LocalDateTime lastLogin) {
        this.passcodeId = passcodeId;
        this.passcode = passcode;
        this.time = time;
        this.lastLogin = lastLogin;
    }

    // Getters and Setters
    public int getPasscodeId() {
        return passcodeId;
    }

    public void setPasscodeId(int passcodeId) {
        this.passcodeId = passcodeId;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "Passcode{" +
                "passcodeId=" + passcodeId +
                ", passcode='" + passcode + '\'' +
                ", time=" + time +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
