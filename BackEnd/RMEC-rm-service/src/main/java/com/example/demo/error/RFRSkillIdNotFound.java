package com.example.demo.error;

public class RFRSkillIdNotFound  extends RuntimeException {
	public RFRSkillIdNotFound () {
		super("RFR skill id not found");
	}
}
