package com.iri.dao3.controller;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.model.Driver;
import com.iri.dao3.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.iri.dao3");
    private final DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/driver/addDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String driverName = req.getParameter("name");
        String licenseNumber = req.getParameter("license_number");
        Driver driver = new Driver(driverName, licenseNumber);
        driverService.create(driver);
        resp.sendRedirect(req.getContextPath() + "/drivers");
    }

}
