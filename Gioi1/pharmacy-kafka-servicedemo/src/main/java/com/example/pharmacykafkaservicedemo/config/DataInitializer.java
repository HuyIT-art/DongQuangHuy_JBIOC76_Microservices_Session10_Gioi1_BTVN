package com.example.pharmacykafkaservicedemo.config;

import com.example.pharmacykafkaservicedemo.entity.Medicine;
import com.example.pharmacykafkaservicedemo.repository.MedicineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(MedicineRepository repository) {
        return args -> {
            // Thêm sẵn 1 loại thuốc có ID = MED123, tồn kho ban đầu = 100
            repository.save(new Medicine("MED123", "Paracetamol 500mg", 100));
            System.out.println("-> Đã khởi tạo dữ liệu mẫu: Paracetamol 500mg (Tồn kho: 100)");
        };
    };
}