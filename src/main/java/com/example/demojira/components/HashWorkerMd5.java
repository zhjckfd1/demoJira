package com.example.demojira.components;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HashWorkerMd5 {
    @Value("${ru.mmtr.demojira.hash.salt}")
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String md5Apache(String st) {
        return DigestUtils.md5Hex(salt + st);
    }
}
