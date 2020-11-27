package com.example.petproject.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

@Component
public class PasswordUtil {

    private final int ITERATIONS = 10000;
    private final int KEY_LENGTH = 256;
    @Value("${salt}")
    private String SALT;

    public String generateSecurePassword(String password) {
        byte[] securePassword = hash(password.toCharArray());
        return Base64.getEncoder().encodeToString(securePassword);
    }

    public byte[] hash(char[] password) {
        PBEKeySpec spec = new PBEKeySpec(password, SALT.getBytes(), ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public boolean verifyUserPassword(String providedPassword, String securedPassword) {
        String newSecurePassword = generateSecurePassword(providedPassword);
        return newSecurePassword.equalsIgnoreCase(securedPassword);
    }
}
