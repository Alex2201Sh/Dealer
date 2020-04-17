package com.github.alex.dealer.service.impl;



import com.github.alex.dealer.dao.UserDao;
import com.github.alex.dealer.dao.impl.DefaultUserDao;
import com.github.alex.dealer.data.User;
import com.github.alex.dealer.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {

    private UserDao userDao = DefaultUserDao.getInstance();

    private static volatile UserService instance;

    public static UserService getInstance() {
        UserService localInstance = instance;
        if (localInstance == null) {
            synchronized (UserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<User> getMembers() {
        return userDao.getMembers();
    }

    @Override
    public Long saveMember(User user) {
        return userDao.save(user);
    }

    @Override
    public User saveAuthUser(User authUser) {

        return authUser;
    }

}
