package com.example.projectcarbook.Controller;

import com.example.projectcarbook.Dao.CarDao;
import com.example.projectcarbook.Dao.CustomerDao;
import com.example.projectcarbook.Model.Car;
import com.example.projectcarbook.Model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
        req.getRequestDispatcher("/car.jsp").forward(req, res);
    }


    private void findOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car car = carDao.findById(id);

        // Lấy thông tin của khách hàng từ bảng khách hàng
        Customer customer = CustomerDao.findById(car.getCustomerId());

        req.setAttribute("car", car);
        req.setAttribute("customer", customer); // Đưa thông tin của khách hàng vào request để sử dụng trong JSP
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
