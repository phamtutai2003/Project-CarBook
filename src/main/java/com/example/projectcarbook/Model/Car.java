package com.example.projectcarbook.Model;

import java.math.BigDecimal;

public class Car {
    private static final long serialVersionUID = 1L;
    private int id;
    private String carName;
    private String carType;
    private String description;
    private BigDecimal rentalPrice;
    private int customerId; // ID của khách hàng sở hữu xe
    private byte[] imageData;
    private String imageFileName;

    public Car() {
    }

    public Car(int id, String carName, String carType, String description, double rentalPrice, int customerId, byte[] imageData, String imageFileName) {
        this.id = id;
        this.carName = carName;
        this.carType = carType;
        this.description = description;
        this.rentalPrice = BigDecimal.valueOf(rentalPrice);
        this.customerId = customerId;
        this.imageData = imageData;
        this.imageFileName = imageFileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getcustomerId() {
        return customerId;
    }

}
