package com.github.rakhmedors.springrestdocs.web.controller;

import com.github.rakhmedors.springrestdocs.repository.BeerRepository;
import com.github.rakhmedors.springrestdocs.web.mapper.BeerMapper;
import com.github.rakhmedors.springrestdocs.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController
{
	private final BeerMapper beerMapper;
	private final BeerRepository beerRepository;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId)
	{
		return new ResponseEntity<>(beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDTO beerDto)
	{
		beerRepository.save(beerMapper.BeerDtoToBeer(beerDto));

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDTO beerDto)
	{
		beerRepository.findById(beerId).ifPresent(beer -> {
			beer.setBeerName(beerDto.getBeerName());
			beer.setBeerStyle(beerDto.getBeerStyle().name());
			beer.setPrice(beerDto.getPrice());
			beer.setUpc(beerDto.getUpc());

			beerRepository.save(beer);
		});

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
