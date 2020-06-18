package com.cpoluru.tconverter.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpoluru.tconverter.domain.ConverterDTO;
import com.cpoluru.tconverter.service.ConverterService;

@RestController()
@RequestMapping(value = "/tconverter")
@CrossOrigin(origins = {"*"})
public class TConverterController {

	@Autowired
    ConverterService converterService;
 
    @PostMapping(path = {"/convert"})
    public Result convert(@RequestBody ConverterDTO dto) {
    	if(Objects.isNull(dto.getInput()) || Objects.isNull(dto.getInput()) || Objects.isNull(dto.getInput()) 
    			|| Objects.isNull(dto.getInput())) {
    		return Result.INVALID;
    	}
        Optional<Double> result = converterService.convert(dto.getFrom(), dto.getTo(), dto.getInput());
        return result.map(value -> value.equals(dto.getResponse())?Result.CORRECT:Result.INCORRECT).orElse(Result.INVALID);
    }
}

enum Result {
	CORRECT, INCORRECT, INVALID;
}