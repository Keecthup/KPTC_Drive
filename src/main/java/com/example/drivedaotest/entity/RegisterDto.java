package com.example.drivedaotest.entity;

import jakarta.validation.constraints.*;

public class RegisterDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private String midllename;

    @NotEmpty
    private String login;

    @Size(min = 8, message = "Долбаеб 8 символов напиши")
    private String password;

    private String confirmPassword;

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotEmpty String getSurname() {
        return surname;
    }

    public void setSurname(@NotEmpty String surname) {
        this.surname = surname;
    }

    public String getMidllename() {
        return midllename;
    }

    public void setMidllename(String midllename) {
        this.midllename = midllename;
    }

    public @Size(min = 8, message = "Долбаеб 8 символов напиши") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, message = "Долбаеб 8 символов напиши") String password) {
        this.password = password;
    }

    public @NotEmpty String getLogin() {
        return login;
    }

    public void setLogin(@NotEmpty String login) {
        this.login = login;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
