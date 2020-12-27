package util.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Class is for encoding string messages
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see MessageDigest
 * @see StringBuilder
 */
public class DigestUtils {

    private DigestUtils(){}

    /**
     * Returns encoded string using MD5 algorithm
     * @param message str for encoding
     * @return encoded str
     */
    public static String md5(String message) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
