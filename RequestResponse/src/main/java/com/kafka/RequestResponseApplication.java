package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.Test;
import com.kafka.service.TestService;

@SpringBootApplication
public class RequestResponseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestResponseApplication.class, args);
	}
	@Autowired
	ObjectMapper mapper;

	@Autowired
	TestService service;

	@Value("${spring.topic}")
	private String topict;

	@KafkaListener(topics = "javatesting")
	private void consumedPayLoad(@Payload String payload) {

		try {
			Test payloadD = mapper.reader().readValue(payload, Test.class);
			System.out.println("Kafka Consumer----->:  " + payloadD.toString());
			if (service.findPayloadById(payloadD.getRandom()) != null && payloadD.getStatus().equals("published")) {
				int d = service.deletePayloadById(payloadD.getRandom());
				if (d > 0) {
					System.out.println(
							"-------------------" + payloadD.getRandom() + "----------------------------------");
					payloadD.setStatus("Consumed");
					service.storePayload(payloadD);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
