package com.github.rakhmedors.springrestdocs.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rakhmedors.springrestdocs.domain.Beer;
import com.github.rakhmedors.springrestdocs.repository.BeerRepository;
import com.github.rakhmedors.springrestdocs.web.model.BeerDTO;
import com.github.rakhmedors.springrestdocs.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
@ExtendWith(value = {RestDocumentationExtension.class})
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "com.github.rakhmedors.springrestdocs.web.mapper")
class BeerControllerTest
{
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BeerRepository beerRepository;

	@Test
	void getBeerById() throws Exception
	{
		given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

		mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
			.param("iscold", "yes")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(document("v1/beer",
				pathParameters(
					parameterWithName("beerId").description("UUID of desired beer to get")),
				requestParameters(
					parameterWithName("iscold").description("Is Beer Cold Query param")
				)
			));
	}

	@Test
	void saveNewBeer() throws Exception
	{
		BeerDTO beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(post("/api/v1/beer/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerDtoJson))
			.andExpect(status().isCreated());
	}

	@Test
	void updateBeerById() throws Exception
	{
		BeerDTO beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerDtoJson))
			.andExpect(status().isNoContent());
	}

	BeerDTO getValidBeerDto()
	{
		return BeerDTO.builder()
			.beerName("Nice Ale")
			.beerStyle(BeerStyleEnum.ALE)
			.price(new BigDecimal("9.99"))
			.upc(123123123123L)
			.build();
	}
}