package com.github.alex.dealer.service;


import com.github.alex.dealer.data.User;

import java.util.List;

public interface UserService {
    List<User> getMembers();

    Long saveMember(User user);

    User saveAuthUser(User authUser);
}
