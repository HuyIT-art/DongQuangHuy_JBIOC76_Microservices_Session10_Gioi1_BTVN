package com.example.pharmacykafkaservicedemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    private String medicineId;
    private String name;
    private int stock;

    public Medicine() {}

    public Medicine(String medicineId, String name, int stock) {
        this.medicineId = medicineId;
        this.name = name;
        this.stock = stock;
    }

    // Getters và Setters
    public String getMedicineId() { return medicineId; }
    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
