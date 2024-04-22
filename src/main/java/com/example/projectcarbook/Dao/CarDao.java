package com.example.projectcarbook.Dao;

import com.example.projectcarbook.Dao.connection.MyConnection;
import com.example.projectcarbook.Model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Car car = extractCarFromResultSet(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return cars;
    }

    public Car findById(int id) {
        Car car = null;
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE ID = ?")) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    car = extractCarFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây nếu cần thiết
        }
        return car;
    }

    public void addCar(Car car) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Car (CarName, CarType, Description, RentalPrice, CustomerID) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, car.getCarName());
            preparedStatement.setString(2, car.getCarType());
            preparedStatement.setString(3, car.getDescription());
            preparedStatement.setBigDecimal(4, car.getRentalPrice());
            preparedStatement.setInt(5, car.getcustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây nếu cần thiết
        }
    }

    private Car extractCarFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("ID"));
        car.setCarName(resultSet.getString("CarName"));
        car.setCarType(resultSet.getString("CarType"));
        car.setDescription(resultSet.getString("Description"));
        car.setRentalPrice(resultSet.getBigDecimal("RentalPrice"));
        car.setcustomerId(resultSet.getInt("CustomerID"));
        return car;
    }
}
