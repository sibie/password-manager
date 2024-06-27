package cbamz.encryption.aes;

import cbamz.storage.FileStorage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Encryptor {
    private static final String filename = "aes.ser";

    private Map<String, KeyData> keyMap;

    public Encryptor() {
        loadKeys();
    }

    @SuppressWarnings("unchecked")
    public void loadKeys() {
        // Load key map with data from file storage.
        keyMap = (Map<String, KeyData>) FileStorage.read(filename);

        // If null, we initialize a new map since file storage does not exist.
        if(keyMap == null)
            keyMap = new HashMap<String, KeyData>();
    }

    public void saveKeys() {
        FileStorage.write(filename, keyMap);
    }

    public String encrypt(String id, String password)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = AESUtil.generateKey();
        byte[] iv = AESUtil.generateIv();
        keyMap.put(id, new KeyData(secretKey, iv));
        return AESUtil.encrypt(password, secretKey, iv);
    }

    public String decrypt(String id, String ciphertext)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyData keyData = keyMap.get(id);
        return AESUtil.decrypt(ciphertext, keyData.getSecretKey(), keyData.getIv());
    }
}
