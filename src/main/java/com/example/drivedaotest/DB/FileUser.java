package com.example.drivedaotest.DB;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FileUser {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

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


  public String getUserSurname() {
    return userSurname;
  }

  public void setUserSurname(String userSurname) {
    this.userSurname = userSurname;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserMiddleName() {
    return userMiddleName;
  }

  public void setUserMiddleName(String userMiddleName) {
    this.userMiddleName = userMiddleName;
  }

}
