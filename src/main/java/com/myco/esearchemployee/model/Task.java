package com.myco.esearchemployee.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "task")
public class Task {

	@Id
	private String taskId;
	private String title;
	private String description;
	private String estimatedTime;
	private List<Employee> employee;
	
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", title=" + title + ", description=" + description + ", estimatedTime="
				+ estimatedTime + ", employee=" + employee + "]";
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

}
