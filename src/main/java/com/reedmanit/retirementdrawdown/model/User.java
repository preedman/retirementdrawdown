package com.reedmanit.retirementdrawdown.model;

//import jakarta.persistence.Entity;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;



public class User {

    private String username;
    private String passwordSalt;
    private String passwordHash;
    private Role role;

    public User(String username, String password, Role role) {
        this.setUsername(username);
        this.setRole(role);
        this.setPasswordSalt(RandomStringUtils.random(32));
        this.setPasswordHash(DigestUtils.sha1Hex(password + getPasswordSalt()));
    }

    public boolean checkPassword(String password) {
        return this.getPasswordHash().equals(DigestUtils.sha1Hex(password + getPasswordSalt()));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
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
