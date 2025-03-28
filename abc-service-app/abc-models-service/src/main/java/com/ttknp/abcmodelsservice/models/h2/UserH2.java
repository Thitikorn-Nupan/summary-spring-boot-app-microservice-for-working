package com.ttknp.abcmodelsservice.models.h2;


public class UserH2 {
    private Long id;
    private String username;
    private String mail;

    public UserH2(Long id, String username, String mail) {
        this.id = id;
        this.username = username;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
