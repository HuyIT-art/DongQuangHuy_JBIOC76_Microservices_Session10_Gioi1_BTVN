package com.example.pharmacykafkaservicedemo.service;

import com.example.pharmacykafkaservicedemo.dto.OrderEvent;
import com.example.pharmacykafkaservicedemo.entity.Medicine;
import com.example.pharmacykafkaservicedemo.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryConsumerService {

    @Autowired
    private MedicineRepository medicineRepository;

    // Lắng nghe topic 'medicine-stock-events' với group-id 'inventory-group'
    @KafkaListener(topics = "medicine-stock-events", groupId = "inventory-group")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        System.out.println("<- Consumer [Inventory] nhận được OrderEvent: " + orderEvent.getOrderId());

        String medicineId = orderEvent.getMedicineId();
        int quantityToDeduct = orderEvent.getQuantity();

        // 1. Tìm thuốc trong Database
        Optional<Medicine> medicineOptional = medicineRepository.findById(medicineId);

        if (medicineOptional.isPresent()) {
            Medicine medicine = medicineOptional.get();

            // 2. Thực hiện nghiệp vụ trừ số lượng tồn kho: stock = stock - quantity
            int newStock = medicine.getStock() - quantityToDeduct;
            medicine.setStock(newStock);

            // 3. Cập nhật lại vào Database
            medicineRepository.save(medicine);

            System.out.println("==> Đã trừ kho thành công cho thuốc [" + medicine.getName() +
                    "]. Tồn kho mới: " + newStock);
        } else {
            System.err.println("X Lỗi: Không tìm thấy thuốc có mã: " + medicineId);
        }
    }
}