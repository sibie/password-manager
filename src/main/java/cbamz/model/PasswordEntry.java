package cbamz.model;

import java.io.Serial;
import java.io.Serializable;

public class PasswordEntry implements Serializable {
    @Serial
    private static final long serialVersionUID = -1555615943146478719L;

    // Encrypted password.
    private String ciphertext;

    // Optional information can be added for reference.
    private String username;
    private String description;
    private String notes;


    public PasswordEntry(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
