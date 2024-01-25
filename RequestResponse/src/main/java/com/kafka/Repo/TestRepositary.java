package com.kafka.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kafka.entity.Test;

public interface TestRepositary extends JpaRepository<Test, Integer> {
	
}
