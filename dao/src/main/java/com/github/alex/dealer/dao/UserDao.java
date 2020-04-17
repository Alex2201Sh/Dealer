package com.github.alex.dealer.dao;

import com.github.alex.dealer.data.User;

import java.util.List;

public interface UserDao {
    List<User> getMembers();

    Long save(User user);
}
