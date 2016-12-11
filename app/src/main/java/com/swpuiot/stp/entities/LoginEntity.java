package com.swpuiot.stp.entities;

/**
 * Created by DELL on 2016/12/11.
 */
public class LoginEntity {

    /**
     * method : login
     * username : zhouhzou
     * password : 123456
     */

    private String method;
    private String username;
    private String password;

    public LoginEntity(String method, String username, String password) {
        this.method = method;
        this.password = password;
        this.username = username;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

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
}
