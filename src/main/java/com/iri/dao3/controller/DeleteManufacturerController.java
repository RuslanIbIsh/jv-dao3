package com.iri.dao3.controller;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.iri.dao3");
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long manufacturerId = Long.parseLong(req.getParameter("id"));
        manufacturerService.delete(manufacturerId);
        resp.sendRedirect(req.getContextPath() + "/manufacturers");
    }
}
