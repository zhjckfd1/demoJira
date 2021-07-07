package com.example.demojira.components;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class HashWorkerMd5 {
    private String salt;  // = "42";

    public HashWorkerMd5(){
        this.salt = "42";
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    //bean через параметры метода
    public String md5Apache(String st) {
        return DigestUtils.md5Hex(salt + st);
    }
}
