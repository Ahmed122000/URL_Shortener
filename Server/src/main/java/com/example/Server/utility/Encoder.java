package com.example.Server.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Encoder {
    private static final int SHA256_DIGEST_LENGTH = 32;

    /**
     * Hashes a URL using the SHA-256 algorithm and returned the shorted hash
     * @param url the URL to hash
     * @param length the desired length of the hash
     * @return A shortened hash of the given URL
     */
    public String hashUrl(String url, int length)  {
        if(url==null || url.isBlank()){
            throw new IllegalArgumentException("URL cannot be null or empty.");
        }
        if(length <= 0 || length > SHA256_DIGEST_LENGTH)
            throw new IllegalArgumentException("Length must be between 1 and " + SHA256_DIGEST_LENGTH);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(url.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return toHex(digest).substring(0, length);
        }catch (NoSuchAlgorithmException ex){
            throw new RuntimeException("Sha-256 algorith not available. ", ex);
        }
    }

    /**
     * Converts a byte array to a hexadecimal string
     * @param bytes the bytes array to convert
     * @return A hexadecimal representation of the byte array
     */
    private String toHex(byte[] bytes){
        StringBuilder hexString = new StringBuilder();
        for(byte b: bytes){
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
