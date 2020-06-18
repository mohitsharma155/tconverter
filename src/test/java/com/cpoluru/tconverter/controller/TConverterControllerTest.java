package com.cpoluru.tconverter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cpoluru.tconverter.config.CorsConfig;
import com.cpoluru.tconverter.config.JacksonConfiguration;
import com.cpoluru.tconverter.domain.ConverterDTO;
import com.cpoluru.tconverter.domain.Unit;
import com.cpoluru.tconverter.service.ConverterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Import(value = {JacksonConfiguration.class, CorsConfig.class})
@Disabled
public class TConverterControllerTest {
	//@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	private ConverterService converterService;

	// **************************************************************************************************
	// Test preparation
	// **************************************************************************************************
	@BeforeEach
	public void setUp() {
		converterService = Mockito.mock(ConverterService.class);
		mockMvc = MockMvcBuilders.standaloneSetup(TConverterController.class, ConverterService.class).build();
	}

	@AfterEach
	public void tearDown() {
		mockMvc = null;
	}

	// **************************************************************************************************
	//  Tests
	//**************************************************************************************************

	@Test
	void whenValidInput_thenReturns200() throws Exception {
		// Given
		final ConverterDTO dto = new ConverterDTO(84.2, Unit.FAHRENHEIT, Unit.RANKINE, 543.9);

		// When
	    when(converterService.convert(any(), any(), any())).thenCallRealMethod();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/tconverter/convert")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto));

		// Then
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertEquals(Result.CORRECT.name(), actualResponseBody.trim());
	}
	
	@Test
	void test_C2FValid() throws Exception {
		// Given
		final ConverterDTO dto = new ConverterDTO(-45.14, Unit.CELSIUS, Unit.KELVIN, 227.51);

		// When
		when(converterService.convert(any(), any(), any())).thenCallRealMethod();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/tconverter/convert")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto));

		// Then
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertEquals(Result.CORRECT.name(), actualResponseBody.trim());
	}

}
