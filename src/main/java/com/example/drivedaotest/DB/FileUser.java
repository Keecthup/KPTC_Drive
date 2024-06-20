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
