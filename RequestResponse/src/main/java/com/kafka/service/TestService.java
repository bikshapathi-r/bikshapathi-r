package com.kafka.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.Repo.TestRepositary;
import com.kafka.entity.Test;

@Service
public class TestService {
	@Autowired
	TestRepositary repo;

	public Test storePayload(Test object) {

		return repo.saveAndFlush(object);
	}

	public Set<Test> findPayloadById(int id) {
		Set<Test> test = new HashSet<>();
		test.add(repo.findById(id).get());
		if (test != null && test.size() > 0)
			return test;

		return null;
	}

	public int deletePayloadById(int id) {
		try {
			repo.deleteById(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
