package com.swpuiot.stp.entities;

/**
 * Created by DELL on 2016/12/11.
 */
public class RegisterEntity {

    /**
     * method : regist
     * username : zhouhzou
     * password : 123456
     * nickname : zhouzhou
     * email : 123@qq.com
     */

    private String method;
    private String username;
    private String password;
    private String nickname;
    private String email;

    public RegisterEntity() {
    }

    public RegisterEntity(String method, String username, String password, String nickname, String email) {
        this.method = method;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
