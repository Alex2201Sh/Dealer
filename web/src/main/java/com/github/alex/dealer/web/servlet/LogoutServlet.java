package com.github.alex.dealer.web.servlet;

import com.github.alex.dealer.service.SecurityService;
import com.github.alex.dealer.web.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("authorizationUser");
        request.getSession().invalidate();
        WebUtils.forward("login", request, response);
    }
}
