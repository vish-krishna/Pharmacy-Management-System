package com.pharmacy.usermanagement.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String userId;
    private String userName;
    @Column(unique = true,nullable = false)
    private String userEmail;
    private String userPassword;
    private String userContact;

    private String userRole;

    public Users() {
    }

    public Users(String userId, String userName, String userEmail, String userPassword, String userContact, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userContact = userContact;
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userContact=" + userContact +
                '}';
    }
}
