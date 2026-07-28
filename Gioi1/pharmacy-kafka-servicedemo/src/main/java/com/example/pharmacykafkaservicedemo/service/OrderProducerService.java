package com.example.pharmacykafkaservicedemo.service;

import com.example.pharmacykafkaservicedemo.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    private static final String TOPIC_NAME = "medicine-stock-events";

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderEvent(OrderEvent orderEvent) {
        // Tham số 1: tên topic
        // Tham số 2: Message Key (medicineId) -> giải quyết thử thách phân vùng
        // Tham số 3: Đối tượng sự kiện (payload)
        kafkaTemplate.send(TOPIC_NAME, orderEvent.getMedicineId(), orderEvent);
        System.out.println("-> Đã gửi OrderEvent lên Kafka: " + orderEvent.getOrderId());
    }
}
