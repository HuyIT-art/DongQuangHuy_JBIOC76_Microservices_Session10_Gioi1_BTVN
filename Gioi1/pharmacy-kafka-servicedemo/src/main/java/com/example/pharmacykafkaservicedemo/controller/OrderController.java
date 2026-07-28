package com.example.pharmacykafkaservicedemo.controller;


import com.example.pharmacykafkaservicedemo.dto.OrderEvent;
import com.example.pharmacykafkaservicedemo.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderProducerService orderProducerService;

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam String medicineId, @RequestParam int quantity) {
        // 1. Khởi tạo đối tượng OrderEvent
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        OrderEvent orderEvent = new OrderEvent(orderId, medicineId, quantity, LocalDateTime.now());

        // 2. Gọi Producer gửi sự kiện lên Kafka
        orderProducerService.sendOrderEvent(orderEvent);

        // 3. Phản hồi kết quả thanh toán thành công
        return ResponseEntity.ok("Thanh toán thành công! Mã đơn hàng: " + orderId);
    }
}
