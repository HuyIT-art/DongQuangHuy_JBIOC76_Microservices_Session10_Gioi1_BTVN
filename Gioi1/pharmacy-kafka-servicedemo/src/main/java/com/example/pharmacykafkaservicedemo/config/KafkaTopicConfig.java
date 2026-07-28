package com.example.pharmacykafkaservicedemo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // 1. Topic nhập/xuất kho: 3 partitions
    @Bean
    public NewTopic medicineStockEventsTopic() {
        return TopicBuilder.name("medicine-stock-events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    // 2. Topic cập nhật giá: 1 partition (đảm bảo thứ tự tuyệt đối)
    @Bean
    public NewTopic medicinePriceUpdatesTopic() {
        return TopicBuilder.name("medicine-price-updates")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // 3. Topic thông báo: 2 partitions
    @Bean
    public NewTopic pharmacyNotificationsTopic() {
        return TopicBuilder.name("pharmacy-notifications")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
