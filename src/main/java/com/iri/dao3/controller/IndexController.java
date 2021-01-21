package com.iri.dao3.controller;

import java.io.IOException;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String timeValue = LocalTime.now().toString();
        req.setAttribute("time", timeValue);
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
