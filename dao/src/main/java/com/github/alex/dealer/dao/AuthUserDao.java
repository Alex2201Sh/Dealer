package com.github.alex.dealer.dao;

import com.github.alex.dealer.data.AuthUser;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    long saveAuthUser(AuthUser user);

    short checkExistUser(String login);
}
