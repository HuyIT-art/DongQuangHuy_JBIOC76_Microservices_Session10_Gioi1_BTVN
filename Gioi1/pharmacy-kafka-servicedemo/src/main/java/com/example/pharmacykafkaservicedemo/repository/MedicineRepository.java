package com.example.pharmacykafkaservicedemo.repository;


import com.example.pharmacykafkaservicedemo.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
}
