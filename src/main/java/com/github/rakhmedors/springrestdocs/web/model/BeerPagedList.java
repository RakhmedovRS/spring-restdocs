package com.github.rakhmedors.springrestdocs.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
public class BeerPagedList extends PageImpl<BeerDTO>
{
	public BeerPagedList(List<BeerDTO> content, Pageable pageable, long total)
	{
		super(content, pageable, total);
	}

	public BeerPagedList(List<BeerDTO> content)
	{
		super(content);
	}
}
