package com.example.library.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
	
	@Bean
	NewTopic auditLogTopic() {
		return TopicBuilder
				.name("book-audit-log")
				.partitions(1)
				.replicas(1)
				.build();
	}
}
