package com.enviro.assessment.grad001.MontelWood;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Service.WasteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EnviroAssessmentApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private WasteService wasteService;

	@Test
	public void testHealthEndpoint() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/waste/health", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Waste Management Service is running", response.getBody());
	}

	@Test
	public void testCreateAndGetWasteCategory() {
		// Create a test category
		WasteCategoryEntity category = new WasteCategoryEntity();
		category.setName("Plastic");
		category.setDescription("Plastic waste items");

		// Save the category
		ResponseEntity<WasteCategoryEntity> createResponse = restTemplate.postForEntity(
				"/api/waste/categories",
				category,
				WasteCategoryEntity.class
		);

		// Check if creation was successful
		assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
		assertNotNull(createResponse.getBody());
		assertNotNull(createResponse.getBody().getId());

		// Get the created category
		ResponseEntity<WasteCategoryEntity> getResponse = restTemplate.getForEntity(
				"/api/waste/categories/" + createResponse.getBody().getId(),
				WasteCategoryEntity.class
		);

		// Check if retrieval was successful
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertEquals("Plastic", getResponse.getBody().getName());
	}

	@Test
	public void testDeleteWasteCategory() {
		// Create a category to delete
		WasteCategoryEntity category = new WasteCategoryEntity();
		category.setName("Glass");
		category.setDescription("Glass waste items");

		ResponseEntity<WasteCategoryEntity> createResponse = restTemplate.postForEntity(
				"/api/waste/categories",
				category,
				WasteCategoryEntity.class
		);

		Long categoryId = createResponse.getBody().getId();

		// Delete the category
		restTemplate.delete("/api/waste/categories/" + categoryId);

		// Try to get deleted category
		ResponseEntity<WasteCategoryEntity> getResponse = restTemplate.getForEntity(
				"/api/waste/categories/" + categoryId,
				WasteCategoryEntity.class
		);

		assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
	}




}
