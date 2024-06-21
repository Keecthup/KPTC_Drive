package com.example.drivedaotest.entity;

import jakarta.persistence.*;

@Entity
public class FileUser {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  private String login;

  private String password;

  private String surname;

  private String name;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String userSurname) {
    this.surname = userSurname;
  }

  public String getName() {
    return name;
  }

  public void setName(String userName) {
    this.name = userName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String userMiddleName) {
    this.middleName = userMiddleName;
  }

  private String middleName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }



}
