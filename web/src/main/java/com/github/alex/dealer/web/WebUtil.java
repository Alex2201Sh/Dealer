package com.github.alex.dealer.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

    public static void forward(String page, HttpServletRequest rq, HttpServletResponse rs) {
        try {
            rq.getRequestDispatcher("/" + page + ".jsp").forward(rq, rs);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void redirect(String page, HttpServletRequest rq, HttpServletResponse rs) {
        try {
            rs.sendRedirect(rq.getContextPath() + page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
