package com.cpoluru.tconverter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConverterDTO {
	private Double input;
	private Unit from;
	private Unit to;
	private Double response;
}
