package com.reedmanit.retirementdrawdown.model;

//import jakarta.persistence.Entity;


import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;


public class User {

    private String username;

    private String passwordHash;
    private Role role;

    public User(String username, String password, Role role) {
        this.setUsername(username);
        this.setRole(role);

        this.setPasswordHash(password);
    }

    public boolean checkPassword(String password) {

        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        if (sha256hex.equals(this.passwordHash)) {
            return true;
        } else {
            return false;
        }


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
