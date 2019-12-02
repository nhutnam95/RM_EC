package com.example.demo.error;

public class RFRRoleIdNotFound  extends RuntimeException {
	public RFRRoleIdNotFound () {
		super("RFR role id not found");
	}
}
