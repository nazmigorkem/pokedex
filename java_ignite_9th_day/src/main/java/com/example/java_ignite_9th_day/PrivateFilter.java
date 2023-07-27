package com.example.java_ignite_9th_day;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "PrivateFilter", urlPatterns = "/private/*")
public class PrivateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        boolean isUser = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user") && cookie.getValue().equals("admin")) {
                isUser = true;
                break;
            }
        }
        if (isUser) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
