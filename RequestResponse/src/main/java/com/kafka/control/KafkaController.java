package com.kafka.control;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kafka.entity.Test;
import com.kafka.events.Producer;
import com.kafka.service.TestService;

@Controller
public class KafkaController {

	@Autowired
	Producer producer;

	@Autowired
	TestService service;

	@PostMapping("/sendPayloadKafka")
	ResponseEntity<Set<Test>> bindPayloadToEntity(@RequestBody Test payload) throws InterruptedException {
		producer.publishToKafka(payload);
		System.out.println("Random id     " + payload.getRandom());
		Thread.sleep(500);

		Set<Test> verify = service.findPayloadById(payload.getRandom());
		if (verify != null)

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(verify);
		else

			verify.add(payload);

		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(verify);
	}
}
