package com.kafka.entity;

import jakarta.annotation.Generated;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Test")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int random;
	public int getRandom() {
		return random;
	}

	public void setRandom(int random) {
		this.random = random;
	}

	private int a;
	private int b;
	private String status;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
