package com.swpuiot.stp.entities;

import java.io.Serializable;

/**
 * Created by DELL on 2016/12/14.
 */
public class ResponseEntity implements Serializable{

    /**
     * nickname : zhouzhou
     * state : 1
     * email : 1095121033@qq.com
     * username : zhouzhou
     */

    private String nickname;
    private String state;
    private String email;
    private String username;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", state='" + state + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
