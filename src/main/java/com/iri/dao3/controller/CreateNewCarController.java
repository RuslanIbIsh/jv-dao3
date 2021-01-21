package com.iri.dao3.controller;

import com.iri.dao3.lib.Injector;
import com.iri.dao3.model.Car;
import com.iri.dao3.model.Manufacturer;
import com.iri.dao3.service.CarService;
import com.iri.dao3.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewCarController extends HttpServlet {
    private static Injector injector = Injector.getInstance("com.iri.dao3");
    private final CarService carService =
            (CarService) injector.getInstance(CarService.class);
    private ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/car/addCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String carModel = req.getParameter("model");
        Long manufacturerId = Long.valueOf(req.getParameter("manufacturerId"));
        Manufacturer manufacturer = manufacturerService.get(manufacturerId);
        Car car = new Car(carModel, manufacturer);
        carService.create(car);
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
