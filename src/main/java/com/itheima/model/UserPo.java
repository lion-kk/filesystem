package com.itheima.model;

public class UserPo {
    private String account;
    private String password;
    private long id;
    private String token;
    private int adminflag;

    public int getAdminflag() {
        return adminflag;
    }

    public void setAdminflag(int adminflag) {
        this.adminflag = adminflag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
