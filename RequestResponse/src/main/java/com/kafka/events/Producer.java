package com.kafka.events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.Repo.TestRepositary;
import com.kafka.entity.Test;
import com.kafka.service.TestService;

@Service
public class Producer {

	@Autowired
	KafkaTemplate<String, String> kafkatemplate;

	@Autowired
	ObjectMapper mapper;

	@Value("${spring.topic}")
	private String topic;

	@Autowired
	private TestService service;

	public Test publishToKafka(Test test) {

		try {
			test.setStatus("published");
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			kafkatemplate.send(topic, mapper.writeValueAsString(test));
			service.storePayload(test);
			return test;
		} catch (Exception e) {
			test.setStatus(e.getMessage());
			return test;
		}
	}
}
