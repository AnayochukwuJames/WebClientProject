package org.example.webclientproject.service;

import org.example.webclientproject.model.City;
import org.example.webclientproject.model.Countries;
import org.example.webclientproject.model.StateCapital;

import java.util.List;

public interface CityService {
    List<Countries> getCountryDetails();

    List<City> getCitiesInNigeria();

    List<StateCapital> getStatesAndCapitalsInNigeria();

    List<City> getCitiesInLagos();

    List<City> getCitiesInEnugu();
}
