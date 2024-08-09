package org.example.webclientproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.webclientproject.model.City;
import org.example.webclientproject.model.Countries;
import org.example.webclientproject.model.StateCapital;
import org.example.webclientproject.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("cities")
    List<Countries> getCountryDetails(){
        return cityService.getCountryDetails();

    }

    @GetMapping("cities/nigeria")
    public  List<City> getCitiesInNigeria(){
        return cityService.getCitiesInNigeria();
    }
    @GetMapping("states/nigeria")
    public List<StateCapital> getStatesAndCapitalsInNigeria(){
        return cityService.getStatesAndCapitalsInNigeria();
    }

    @GetMapping("lagos")
    public List<City> getCitiesInLagos(){
        return cityService.getCitiesInLagos();
    }

    @GetMapping("enugu")
    public List<City> getCitiesInEnugu(){
        return cityService.getCitiesInEnugu();
    }
}
