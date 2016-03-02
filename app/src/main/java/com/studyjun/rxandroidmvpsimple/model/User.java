package com.studyjun.rxandroidmvpsimple.model;

/**
 * Created by studyjun on 2016/3/2.
 * model bean
 */
public class User {

    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
