package com.example.projectcarbook.Controller;

import com.example.projectcarbook.Dao.BookingDao;
import com.example.projectcarbook.Model.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookingServlet", value = "/bookings")
public class BookingController extends HttpServlet {
    private BookingDao bookingDao = new BookingDao();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addGet":
                addBookingGet(req, resp);
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
                addBooking(req, resp);
                break;
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Booking> bookings = bookingDao.findAll();
        req.setAttribute("bookings", bookings);
        req.getRequestDispatcher("/booking/list.jsp").forward(req, res);
    }

    private void findOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Booking booking = bookingDao.findById(id);
        req.setAttribute("booking", booking);
        req.getRequestDispatcher("/booking/detail.jsp").forward(req, res);
    }

    private void addBookingGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher("/booking/create.jsp").forward(req, res);
    }

    private void addBooking(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int carId = Integer.parseInt(req.getParameter("carId"));
        Date startTime;
        Date endTime;
        try {
            startTime = dateFormatter.parse(req.getParameter("startTime"));
            endTime = dateFormatter.parse(req.getParameter("endTime"));
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the error gracefully or redirect with an error message
            return;
        }
        String rentalType = req.getParameter("rentalType");
        double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
        String status = req.getParameter("status");

        Booking booking = new Booking();
        booking.setCustomerId(customerId);
        booking.setCarId(carId);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setRentalType(rentalType);
        booking.setTotalPrice(totalPrice);
        booking.setStatus(status);

        bookingDao.addBooking(booking);
        res.sendRedirect("bookings");
    }
}
