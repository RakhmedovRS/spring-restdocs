package com.github.rakhmedors.springrestdocs.repository;

import com.github.rakhmedors.springrestdocs.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>
{
}
