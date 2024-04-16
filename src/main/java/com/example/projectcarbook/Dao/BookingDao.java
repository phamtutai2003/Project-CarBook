package com.example.projectcarbook.Dao;

import com.example.projectcarbook.Dao.connection.MyConnection;
import com.example.projectcarbook.Model.Booking;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    // Phương thức để lấy danh sách tất cả các booking từ cơ sở dữ liệu
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Booking");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setCustomerId(resultSet.getInt("customerId"));
                booking.setCarId(resultSet.getInt("carId"));
                booking.setStartTime(resultSet.getDate("startTime"));
                booking.setEndTime(resultSet.getDate("endTime"));
                booking.setRentalType(resultSet.getString("rentalType"));
                booking.setTotalPrice(resultSet.getDouble("totalPrice"));
                booking.setStatus(resultSet.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Phương thức để thêm một booking mới vào cơ sở dữ liệu
    public void addBooking(Booking booking) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Booking (customerId, carId, startTime, endTime, rentalType, totalPrice, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, booking.getCustomerId());
            preparedStatement.setInt(2, booking.getCarId());
            preparedStatement.setDate(3, new java.sql.Date(booking.getStartTime().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(booking.getEndTime().getTime()));
            preparedStatement.setString(5, booking.getRentalType());
            preparedStatement.setDouble(6, booking.getTotalPrice());
            preparedStatement.setString(7, booking.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để tìm kiếm một booking dựa trên id
    public Booking findById(int id) {
        Booking booking = null;
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Booking WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    booking = new Booking();
                    booking.setId(resultSet.getInt("id"));
                    booking.setCustomerId(resultSet.getInt("customerId"));
                    booking.setCarId(resultSet.getInt("carId"));
                    booking.setStartTime(resultSet.getDate("startTime"));
                    booking.setEndTime(resultSet.getDate("endTime"));
                    booking.setRentalType(resultSet.getString("rentalType"));
                    booking.setTotalPrice(resultSet.getDouble("totalPrice"));
                    booking.setStatus(resultSet.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }
}
