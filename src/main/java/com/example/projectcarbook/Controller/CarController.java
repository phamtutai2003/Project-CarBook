package com.example.projectcarbook.Controller;

import com.example.projectcarbook.Dao.CarDao;
import com.example.projectcarbook.Model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "CarServlet", value = "/cars")
public class CarController extends HttpServlet {
    private CarDao carDao = new CarDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addGet":
                addCarGet(req, resp);
                break;
            case "detail":
                findOne(req, resp);
                break;
            default:
                findAll(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addCar(req, resp);
                break;
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Car> cars = carDao.findAll();
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("car.jsp").forward(req, res);
    }


    private void findOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car car = carDao.findById(id);
        req.setAttribute("car", car);
        req.getRequestDispatcher("car-single.jsp").forward(req, res);
    }

    private void addCarGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect("car/create.jsp");
    }

    private void addCar(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String carName = req.getParameter("carName");
        String carType = req.getParameter("carType");
        String description = req.getParameter("description");
        double rentalPrice = Double.parseDouble(req.getParameter("rentalPrice"));
        int customerID = Integer.parseInt(req.getParameter("customerID"));

        Car car = new Car();
        car.setCarName(carName);
        car.setCarType(carType);
        car.setDescription(description);
        car.setRentalPrice(BigDecimal.valueOf(rentalPrice));
        car.setcustomerId(customerID);

        carDao.addCar(car);
        res.sendRedirect("cars");
    }
}
