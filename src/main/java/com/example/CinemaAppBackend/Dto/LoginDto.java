package com.example.CinemaAppBackend.Dto;

public class LoginDto {
    private String username;
    private String password;
    private String email;

    private String usernameOrEmail;

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    //constructors
    public LoginDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public LoginDto() {
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }
}
