package com.github.alex.dealer.service.impl;


import com.github.alex.dealer.dao.AuthUserDao;
import com.github.alex.dealer.dao.impl.DefaultAuthUserDao;
import com.github.alex.dealer.data.AuthUser;
import com.github.alex.dealer.data.Role;
import com.github.alex.dealer.service.SecurityService;

public class DefaultSecurityService implements SecurityService {
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    private static volatile SecurityService instance;

    public static SecurityService getInstance() {
        SecurityService localInstance = instance;
        if (localInstance == null) {
            synchronized (SecurityService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultSecurityService();
                }
            }
        }
        return localInstance;
    }

    public AuthUser login(String login, String password) {
        AuthUser user = authUserDao.getByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static boolean checkAlreadyExistsUser(String login){
        boolean flag = false;
        DefaultAuthUserDao defaultAuthUserDao = new DefaultAuthUserDao();
        if(defaultAuthUserDao.getByLogin(login).equals(login)) {
            flag = true;
        }
        return flag;
    }

    public static void createUser(String login, String password, Role role){
        if (checkAlreadyExistsUser(login) == false) {
            DefaultAuthUserDao defaultAuthUserDao = new DefaultAuthUserDao();
            defaultAuthUserDao.saveAuthUser(new AuthUser(login, password, role));
        }
    }
}
