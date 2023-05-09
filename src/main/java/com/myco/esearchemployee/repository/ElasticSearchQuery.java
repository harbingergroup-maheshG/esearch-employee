package com.myco.esearchemployee.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myco.esearchemployee.model.Employee;
import com.myco.esearchemployee.model.Task;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ElasticSearchQuery {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchQuery.class);

	@Autowired
	private ElasticsearchClient elasticsearchClient;

	private static final String EMPLOYEE_INDEX_NAME = "employee";

	private static final String TASK_INDEX_NAME = "task";

	public String createOrUpdateDocument(Employee employee) throws Exception {
		LOGGER.info(">>>> createOrUpdateDocument() , employee : {}", employee);
		IndexResponse response = elasticsearchClient
				.index(i -> i.index(EMPLOYEE_INDEX_NAME).id(employee.getEmployeeId()).document(employee));
		if (response.result().name().equals("Created")) {
			LOGGER.info("<<<< createOrUpdateDocument() <<<< Document has been successfully created.");
			return new StringBuilder("Document has been successfully created.").toString();
		} else if (response.result().name().equals("Updated")) {
			LOGGER.info("<<<< createOrUpdateDocument() <<<< Document has been successfully updated.");
			return new StringBuilder("Document has been successfully updated.").toString();
		}
		LOGGER.error(">>>> error on createOrUpdateDocument()");
		return new StringBuilder("Error while performing the operation.").toString();
	}

	public String createOrUpdateDocument(Task task) throws Exception {
		LOGGER.info(">>>> createOrUpdateDocument() , employee : {}", task);
		IndexResponse response = elasticsearchClient
				.index(i -> i.index(TASK_INDEX_NAME).id(task.getTaskId()).document(task));
		if (response.result().name().equals("Created")) {
			LOGGER.info("<<<< createOrUpdateDocument() <<<< Document has been successfully created.");
			return new StringBuilder("Document has been successfully created.").toString();
		} else if (response.result().name().equals("Updated")) {
			LOGGER.info("<<<< createOrUpdateDocument() <<<< Document has been successfully updated.");
			return new StringBuilder("Document has been successfully updated.").toString();
		}
		LOGGER.error("<<<< error on createOrUpdateDocument()");
		return new StringBuilder("Error while performing the operation.").toString();
	}

//
//    public String deleteDocumentById(String productId) throws IOException {
//
//        DeleteRequest request = DeleteRequest.of(d -> d.index(EMPLOYEE_INDEX_NAME).id(productId));
//
//        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
//        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
//            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
//        }
//        System.out.println("Product not found");
//        return new StringBuilder("Product with id " + deleteResponse.id() + " does not exist.").toString();
//
//    }
//
	public List<Employee> searchAllDocuments() throws IOException {

		SearchRequest searchRequest = SearchRequest.of(s -> s.index(EMPLOYEE_INDEX_NAME));
		SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Employee.class);
		List<Hit> hits = searchResponse.hits().hits();
		List<Employee> employees = new ArrayList<>();
		for (Hit object : hits) {

			LOGGER.info(">>>> task : {}", ((Employee) object.source()));
			employees.add((Employee) object.source());

		}
		return employees;
	}

	public List<Task> searchAllTaskDocuments() throws IOException {
		LOGGER.info(">>>> searchAllTaskDocuments()>>>>");
		SearchRequest searchRequest = SearchRequest.of(s -> s.index(EMPLOYEE_INDEX_NAME));
		SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Task.class);
		List<Hit> hits = searchResponse.hits().hits();
		List<Task> task = new ArrayList<>();
		for (Hit object : hits) {

			LOGGER.info(">>>> task : {}", ((Task) object.source()));
			task.add((Task) object.source());

		}
		return task;
	}

	public Employee getDocumentById(String employeeId) throws IOException {
		LOGGER.info(">>>> getDocumentByTaskId : employeeId :  {}", employeeId);
		Employee employee = null;
		GetResponse<Employee> response = elasticsearchClient.get(g -> g.index(EMPLOYEE_INDEX_NAME).id(employeeId),
				Employee.class);

		if (response.found()) {
			employee = response.source();
			LOGGER.info("<<<< Employee : {}", employee);
		} else {
			LOGGER.info("<<<< employee not found");
		}

		return employee;
	}

	public Task getDocumentByTaskId(String taskId) throws IOException {
		LOGGER.info(">>>> getDocumentByTaskId : taskId : {}", taskId);
		Task task = null;
		GetResponse<Task> response = elasticsearchClient.get(g -> g.index(TASK_INDEX_NAME).id(taskId), Task.class);

		if (response.found()) {
			task = response.source();
			LOGGER.info("<<<< Task : {}", task);
		} else {
			LOGGER.info("<<<< task not found");
		}

		return task;
	}
}
