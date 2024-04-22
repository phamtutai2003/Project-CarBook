package com.example.projectcarbook.Dao;

import com.example.projectcarbook.Dao.connection.MyConnection;
import com.example.projectcarbook.Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final String INSERT_INTO = "INSERT INTO Customer (Name, Address, Email, Phone) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM Customer";
    private static final String DELETE_BY_ID = "DELETE FROM Customer WHERE ID = ?";
    private final Connection connection;

    public CustomerDao() {
        connection = MyConnection.getInstance();
    }

    public static Customer findById(int customerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        try {
            // Kết nối tới cơ sở dữ liệu
            connection = MyConnection.getConnection();
            // Chuẩn bị truy vấn SQL
            String sql = "SELECT * FROM Customer WHERE customerId = ?";
            preparedStatement = connection.prepareStatement(sql);
            // Thiết lập tham số cho truy vấn
            preparedStatement.setInt(1, customerId);
            // Thực thi truy vấn
            resultSet = preparedStatement.executeQuery();
            // Xử lý kết quả trả về
            if (resultSet.next()) {
                // Tạo đối tượng Customer từ dữ liệu của bảng
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setCustomerName(resultSet.getString("customerName"));
                // Các trường thông tin khác của khách hàng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và các tài nguyên
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public boolean addCustomer(Customer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");

                Customer customer = new Customer(id, name, address, email, phone);
                customers.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public boolean deleteCustomer(int customerId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, customerId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
