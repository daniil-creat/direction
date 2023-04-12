package com.work.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.test.data.model.Region;
import com.work.test.service.RegionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@TestPropertySource(
        locations = "classpath:application.properties")
@WebAppConfiguration
class ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private RegionService regionService;

	@Test
	void createMethodTest() throws Exception {
		Region region = new Region();
		region.setName("name");
		region.setAbbreviation("name");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(region)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Assertions.assertEquals(regionService.getRegions().size(), 1);
	}

	@Test
	void getRegionMethodTest() throws Exception {

	var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/regions")
				.contentType("application/json"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void updateRegionMethodTest() throws Exception {
        var updateRegion = new Region();
        updateRegion.setAbbreviation("new name");
        updateRegion.setName("new name");

		var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/update/1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(updateRegion)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		var region = regionService.getRegionById(Long.valueOf(1));
		Assertions.assertEquals(region.getName(), "new name");
	}

	@Test
	void deleteRegionMethodTest() throws Exception {
		var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/delete/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		var region = regionService.getRegionById(Long.valueOf(1));
		Assertions.assertEquals(region, null);
	}
}
