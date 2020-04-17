package com.github.alex.dealer.service;

import com.github.alex.dealer.data.AuthUser;

public interface SecurityService {
    AuthUser login(String login, String password);
}
