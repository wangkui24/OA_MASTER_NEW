package com.kwang43.boot.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    @Autowired
    private StringEncryptor stringEncryptor;

    public String encrypt(String plainText) {
        return stringEncryptor.encrypt(plainText);
    }

    public String decrypt(String encryptedText) {
        return stringEncryptor.decrypt(encryptedText);
    }
}
