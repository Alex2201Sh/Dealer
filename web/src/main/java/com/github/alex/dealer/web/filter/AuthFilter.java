package com.github.alex.dealer.web.filter;

import com.github.alex.dealer.data.AuthUser;
import com.github.alex.dealer.data.Role;
import com.github.alex.dealer.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password")) &&
                nonNull(session.getAttribute("role"))) {

//            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            Role role  = (Role) session.getAttribute("role");

//            AuthUser authUser = new AuthUser((String) session.getAttribute("login"),
//                    (String)session.getAttribute("password"),
//                    (Role) session.getAttribute("role"));
//            Role role = authUser.getRole();

            moveToMenu(req, res, role);

        }

        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authorizationUser");
//        AuthUser authUser = req.getSession().getAttribute("authorizationUser");
        if (authUser == null) {
            WebUtils.forward("login", req, ((HttpServletResponse) servletResponse));
            return;
        }
        filterChain.doFilter(req, servletResponse);
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final Role role)
            throws ServletException, IOException {


        if (role.equals(Role.ADMINISTRATOR)) {

            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);

        } else if (role.equals(Role.EMPLOYEE)) {

            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);

        } else {

            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}