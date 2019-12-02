package com.example.demo.error;

public class ProjectNotFound  extends RuntimeException{
	public ProjectNotFound(Long id) {
		super("project id:"+ id+"not found");
	}
}
