package com.github.alex.dealer.web.servlet;

import com.github.alex.dealer.data.User;
import com.github.alex.dealer.service.UserService;
import com.github.alex.dealer.service.impl.DefaultUserService;
import com.github.alex.dealer.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = DefaultUserService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebUtils.forward("user", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");


        if (name.equals("") || surname.equals("") || phone.equals("")){
            request.setAttribute("error", "incorrect data");
            WebUtils.forward("user", request, response);
        }

        User user = new User(null, name, surname, phone);
        User saveUser = userService.saveAuthUser(user);
        log.info("user created:{} at {}", saveUser.getId(), LocalDateTime.now());
        request.getSession().setAttribute("userId", saveUser.getId());

        WebUtils.redirect("/user", request, response);
    }
}
