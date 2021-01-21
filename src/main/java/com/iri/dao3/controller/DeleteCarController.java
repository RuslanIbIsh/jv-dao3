package com.iri.dao3.controller;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.service.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.iri.dao3");
    private final CarService carService =
            (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long carId = Long.parseLong(req.getParameter("id"));
        carService.delete(carId);
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
