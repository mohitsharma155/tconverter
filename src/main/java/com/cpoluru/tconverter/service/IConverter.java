package com.cpoluru.tconverter.service;

import java.util.Optional;

import com.cpoluru.tconverter.domain.Unit;

public interface IConverter {
	Optional<Double> convert(Unit from, Unit to, double value);
	
	static Double round(Double value) {
    	return Math.round(value.doubleValue() * 10) / 10.0;
    }
}
