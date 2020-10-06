package com.github.rakhmedors.springrestdocs.web.mapper;

import com.github.rakhmedors.springrestdocs.domain.Beer;
import com.github.rakhmedors.springrestdocs.web.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper
{
	BeerDTO BeerToBeerDto(Beer beer);

	Beer BeerDtoToBeer(BeerDTO dto);
}