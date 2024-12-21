package com.example.Server.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Encoder {

    public String hashUrl(String url, int length)  {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(url.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            StringBuilder hexString = new StringBuilder();
            for(byte b: digest){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.substring(0, length);
        }catch (NoSuchAlgorithmException ex){
            throw new RuntimeException("Sha-1 algorith not available", ex);
        }
    }
}
