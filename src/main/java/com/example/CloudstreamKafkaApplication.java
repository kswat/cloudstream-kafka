package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class CloudstreamKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudstreamKafkaApplication.class, args);
	}

	@Bean
	public Supplier<UUID> stringSupplier() {
		return () -> {
			var uuid = UUID.randomUUID();
			System.out.println(uuid + " -> batch-in");
			return uuid;
		};
	}

	@KafkaListener(id = "sanitized-event", topics = "sanitized-event")
	public void listen(String in) {
		System.out.println("batch-out -> " + in);
	}
}
