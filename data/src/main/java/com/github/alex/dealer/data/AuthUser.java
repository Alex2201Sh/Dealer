package com.github.alex.dealer.data;

public class AuthUser {
    private String login;
    private String password;
    private Role role;
    private Long user_Id;
    private Long id;

    public AuthUser(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public AuthUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public AuthUser(Long id, String login, String password, Role role, Long user_Id) {
        this.id= id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.user_Id = user_Id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Long getUserId() {
        return user_Id;
    }
}
