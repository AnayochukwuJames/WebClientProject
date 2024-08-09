package org.example.webclientproject.service.serviceImp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.webclientproject.model.City;
import org.example.webclientproject.model.Countries;
import org.example.webclientproject.model.DataResponse;
import org.example.webclientproject.model.StateCapital;
import org.example.webclientproject.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public CityServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Countries> getCountryDetails() {
        String uri = "https://countriesnow.space/api/v0.1/countries";
        try {
            Mono<String> response = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class);

            String responseBody = response.block();
            DataResponse dataResponse = objectMapper.readValue(responseBody, DataResponse.class);
            return dataResponse.getData();
        } catch (WebClientResponseException exception) {

            exception.printStackTrace();
            return Collections.emptyList();
        } catch (Exception e) {

            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<City> getCitiesInNigeria() {
        List<Countries> countries = getCountryDetails();
        return countries.stream()
                .filter(country -> "Nigeria".equalsIgnoreCase(country.getCountry()))
                .findFirst()
                .map(Countries::getCities)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<StateCapital> getStatesAndCapitalsInNigeria() {
        List<Countries> countries = getCountryDetails();
        return countries.stream()
                .filter(country -> "Nigeria".equalsIgnoreCase(country.getCountry()))
                .findFirst()
                .map(Countries::getStates)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<City> getCitiesInLagos() {
        List<Countries> countries = getCountryDetails();
        return countries.stream()
                .filter(country -> "Nigeria".equalsIgnoreCase(country.getCountry()))
                .findFirst()
                .map(Countries::getCities)
                .orElse(Collections.emptyList())
                .stream()
                .filter(city -> "Lagos".equalsIgnoreCase(city.getCity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<City> getCitiesInEnugu() {
        List<Countries> countries = getCountryDetails();
        return countries.stream()
                .filter(country -> "Nigeria".equalsIgnoreCase(country.getCountry()))
                .findFirst()
                .map(Countries::getCities)
                .orElse(Collections.emptyList())
                .stream()
                .filter(city -> "Enugu".equalsIgnoreCase(city.getCity()))
                .collect(Collectors.toList());
    }
}
