package com.enviro.assessment.grad001.MontelWood;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
class EnviroAssessmentApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private MockMvc mockMvc;



	@Test
	//I first want to check if the api is up and running.
	public void testApiendpointStatus() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/waste"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
