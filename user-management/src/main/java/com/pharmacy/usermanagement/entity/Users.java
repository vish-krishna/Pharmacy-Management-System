package com.pharmacy.usermanagement.entity;


import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    private String user_Id;
    private String user_name;
    private String user_email;
    private String user_password;
    private int user_contact;

    public Users() {
    }

    public Users(String user_Id, String user_name, String user_email, String user_password, int user_contact) {
        this.user_Id = user_Id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_contact = user_contact;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(int user_contact) {
        this.user_contact = user_contact;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_Id='" + user_Id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_contact=" + user_contact +
                '}';
    }
}
