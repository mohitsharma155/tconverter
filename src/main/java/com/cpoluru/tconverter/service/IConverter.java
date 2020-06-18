package com.cpoluru.tconverter.service;

import java.util.Optional;

import com.cpoluru.tconverter.domain.Unit;

@FunctionalInterface
public interface IConverter {
	Optional<Double> convert(Unit from, Unit to, double value);
}
