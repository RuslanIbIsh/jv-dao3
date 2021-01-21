package com.iri.dao3.controller;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.model.Manufacturer;
import com.iri.dao3.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.iri.dao3");
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/manufacturer/addManufacturer.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String manufacturerName = req.getParameter("name");
        String manufacturerCountry = req.getParameter("country");
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturerService.create(manufacturer);
        resp.sendRedirect(req.getContextPath() + "/manufacturers");
    }
}
