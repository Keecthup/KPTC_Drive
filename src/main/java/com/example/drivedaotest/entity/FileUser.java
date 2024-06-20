package com.example.drivedaotest.entity;

import jakarta.persistence.*;

@Entity
public class FileUser {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  private String userLogin;

  private String userPassword;

  private String userSurname;

  private String userName;

  private String userMiddleName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


}
