package com.example.java_ignite_7th_day;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ATM", value = "/atm")
public class ATM extends HttpServlet {
    int balance;

    @Override
    public void init() throws ServletException {
        balance = 0;
        super.init();
    }

    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionType = req.getParameter("actionType");

        int amount = Integer.parseInt(req.getParameter("amount"));
        PrintWriter writer = resp.getWriter();
        if ("deposit".equals(actionType)) {
            balance += amount;
        } else if ("withdraw".equals(actionType)) {
            if (balance - amount >= 0) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                balance -= amount;
            } else {
                writer.println("Insufficient balance.");
            }
        }

        writer.printf("New balance %d", balance);
    }
}
