package cbamz.model;

import cbamz.encryption.aes.Encryptor;
import cbamz.storage.FileStorage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class PasswordManager {
    private static final String filename = "passwords.ser";

    private Map<String, PasswordEntry> passwordMap;
    private final Encryptor encryptor;

    public PasswordManager() {
        encryptor = new Encryptor();
        loadPasswords();
    }

    public void close() {
        encryptor.saveKeys();
        savePasswords();
    }

    @SuppressWarnings("unchecked")
    public void loadPasswords() {
        // Load password map with data from file storage.
        passwordMap = (Map<String, PasswordEntry>) FileStorage.read(filename);

        // If null, we initialize a new map since file storage does not exist.
        if(passwordMap == null)
            passwordMap = new HashMap<String, PasswordEntry>();
    }

    public void savePasswords() {
        FileStorage.write(filename, passwordMap);
    }

    public String decryptPassword(String id, String ciphertext)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        return encryptor.decrypt(id, ciphertext);
    }

    public PasswordEntry getEntry(String id) {
        return passwordMap.get(id);
    }

    public void addEntry(String id, String password, String username, String description, String notes)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        PasswordEntry entry = new PasswordEntry(encryptor.encrypt(id, password));
        entry.setUsername(username);
        entry.setDescription(description);
        entry.setNotes(notes);
        passwordMap.put(id, entry);
    }

    public void updateEntry(String id, PasswordEntry entry) {
        passwordMap.replace(id, entry);
    }

    public void deleteEntry(String id) {
        passwordMap.remove(id);
    }
}
