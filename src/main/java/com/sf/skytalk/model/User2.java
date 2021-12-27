package com.sf.skytalk.model;

public class User2 {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }
}
