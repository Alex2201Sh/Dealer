package com.github.alex.dealer.service;

import com.github.alex.dealer.dao.AuthUserDao;
import com.github.alex.dealer.data.AuthUser;
import com.github.alex.dealer.data.Role;
import com.github.alex.dealer.service.impl.DefaultSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultSecurityServiceTest {

    @Mock
    AuthUserDao dao;

    @InjectMocks
    DefaultSecurityService service;

    @Test
    void getNonExistLogin(){
        when(dao.getByLogin("login")).thenReturn(null);
        AuthUser au = service.login("login", "password");
        assertNull(au);
    }

    @Test
    void getExistingLogin(){
        when(dao.getByLogin("login")).thenReturn(new AuthUser(1L,"login", "password", Role.ADMINISTRATOR,1L));
        AuthUser au = service.login("login","password");
        assertNotNull(au);
        assertEquals(au.getId(), 1);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getByLogin("login")).thenReturn(new AuthUser("login", "password", Role.ADMINISTRATOR));
        AuthUser au = service.login("login","wrongPassword");
        assertNull(au);
    }


}
