package com.iri.dao3.controller;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.iri.dao3");
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long driverId = Long.parseLong(req.getParameter("id"));
        driverService.delete(driverId);
        resp.sendRedirect(req.getContextPath() + "/drivers");
    }
}
