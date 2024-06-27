package cbamz.encryption.aes;

import javax.crypto.SecretKey;
import java.io.Serial;
import java.io.Serializable;

public class KeyData implements Serializable {
    @Serial
    private static final long serialVersionUID = 3053675928066743491L;

    private final SecretKey secretKey;
    private final byte[] iv;

    public KeyData(SecretKey secretKey, byte[] iv) {
        this.secretKey = secretKey;
        this.iv = iv;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public byte[] getIv() {
        return iv;
    }
}
