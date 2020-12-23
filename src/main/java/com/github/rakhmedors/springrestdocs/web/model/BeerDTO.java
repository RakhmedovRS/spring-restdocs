package com.github.rakhmedors.springrestdocs.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO
{
	@Null
	private UUID id;

	@Null
	private Integer version;

	@Null
	private OffsetDateTime createdDate;

	@Null
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	@Size(min = 3, max = 100)
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyle;

	@Positive
	@NotNull
	private Long upc;

	@Positive
	@NotNull
	private BigDecimal price;

	@Positive
	private Integer quantityOnHand;
}
