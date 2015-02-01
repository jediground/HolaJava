package com.foobar.jdbc;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public User() {
    }

    public User(Date birth, String email, int id, String name) {
        this.birth = birth;
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "birth=" + birth +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
