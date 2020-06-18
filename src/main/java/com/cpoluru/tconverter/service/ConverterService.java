package com.cpoluru.tconverter.service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.cpoluru.tconverter.domain.Unit;
import com.google.common.collect.ImmutableMap;

@Component
public class ConverterService implements IConverter {
	
	private static final Map<String, Function<Double, Double>> formulae;
 
	private static final Function<Double, Double> F2C = f -> (f - 32) * 5 / 9;
	private static final Function<Double, Double> C2F = c -> c * 9 / 5 + 32;
	private static final Function<Double, Double> F2R = f -> f + 459.67;
	private static final Function<Double, Double> R2F = r -> r - 459.67;
	private static final Function<Double, Double> F2K = f -> (f + 459.67) * 5/9;
	private static final Function<Double, Double> K2F = k -> k * 9/5 - 459.67;
	private static final Function<Double, Double> K2C = k -> k - 273.15;
	private static final Function<Double, Double> C2K = c -> c + 273.15;
	private static final Function<Double, Double> K2R = k -> k * 9/5;
	private static final Function<Double, Double> R2K = r -> r * 5/9;
	private static final Function<Double, Double> C2R = c -> (c + 273.15) * 9/5;
	private static final Function<Double, Double> R2C = r -> (r - 491.67) * 5/9;
    
    static {
    	formulae = new ImmutableMap.Builder<String, Function<Double, Double>>()
    			.put(getKey(Unit.CELSIUS, Unit.FAHRENHEIT), C2F)
    			.put(getKey(Unit.FAHRENHEIT, Unit.CELSIUS), F2C)
    			.put(getKey(Unit.FAHRENHEIT, Unit.RANKINE), F2R)
    			.put(getKey(Unit.RANKINE, Unit.FAHRENHEIT), R2F)
    			.put(getKey(Unit.FAHRENHEIT, Unit.KELVIN), F2K)
    			.put(getKey(Unit.KELVIN, Unit.FAHRENHEIT), K2F)
    			.put(getKey(Unit.KELVIN, Unit.CELSIUS), K2C)
    			.put(getKey(Unit.CELSIUS, Unit.KELVIN), C2K)
    			.put(getKey(Unit.KELVIN, Unit.RANKINE), K2R)
    			.put(getKey(Unit.RANKINE, Unit.KELVIN), R2K)
    			.put(getKey(Unit.CELSIUS, Unit.RANKINE), C2R)
    			.put(getKey(Unit.RANKINE, Unit.CELSIUS), R2C)
    			.build();
    }

    @Override
    public Optional<Double> convert(Unit from, Unit to, double value) {
    	if(from == to) {
    		return Optional.ofNullable(value);
    	}
    	Optional<Double> result = Optional.ofNullable(formulae.get(from +"_" + to)).map(f -> round(f.apply(value)));
    	result.ifPresent(v -> System.out.println(round(v)));
    	
    	return result;
    }
    
    private static String getKey(Unit from, Unit to) {
    	return new StringBuilder(from.name()).append("_").append(to.name()).toString();
    }
    
    public static Double round(Double value) {
    	return Math.round(value.doubleValue() * 10) / 10.0;
    }
}