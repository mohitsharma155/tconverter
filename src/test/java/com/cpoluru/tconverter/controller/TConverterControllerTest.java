package com.cpoluru.tconverter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cpoluru.tconverter.TConverterApplication;
import com.cpoluru.tconverter.domain.ConverterDTO;
import com.cpoluru.tconverter.domain.Unit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { TConverterApplication.class })
@AutoConfigureMockMvc
public class TConverterControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	// **************************************************************************************************
	// Test preparation
	// **************************************************************************************************
	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
		mockMvc = null;
	}

	// **************************************************************************************************
	// Tests
	// **************************************************************************************************

	@Test
	void whenValidInput_thenReturns200() throws Exception {
		// Given
		final ConverterDTO dto = new ConverterDTO(84.2, Unit.FAHRENHEIT, Unit.RANKINE, 543.9);

		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/tconverter/convert")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto));

		// Then
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		verifyResonseCorrect(mvcResult);
	}

	@Test
	void test_C2KValid() throws Exception {
		// Given
		final ConverterDTO dto = new ConverterDTO(-45.14, Unit.CELSIUS, Unit.KELVIN, 228.01);

		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/tconverter/convert")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto));

		// Then
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		verifyResonseCorrect(mvcResult);
	}

	@Test
	void test_K2CValid() throws Exception {
		// Given
		final ConverterDTO dto = new ConverterDTO(228.01, Unit.KELVIN, Unit.CELSIUS, -45.14);

		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/tconverter/convert")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto));

		// Then
		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		verifyResonseCorrect(mvcResult);
	}
	
	
	private void verifyResonseCorrect(MvcResult mvcResult)
			throws UnsupportedEncodingException, JsonProcessingException, JsonMappingException {
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		Result result = objectMapper.readValue(actualResponseBody, Result.class);

		assertEquals(Result.CORRECT, result);
	}

}
