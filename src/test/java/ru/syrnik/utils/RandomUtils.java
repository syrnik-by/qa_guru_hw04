package ru.syrnik.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.syrnik.models.State;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    static private final Random rand = new Random();

    static public State getRandomState() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<State> stateList = objectMapper.readValue(new File("src/test/resources/json/state.json"), new TypeReference<>(){});
        return stateList.get(rand.nextInt(stateList.size()));
    }

    static public String getRandomCityByState(State state) {
        List<String> cityList = state.getCity();
        return cityList.get(rand.nextInt(cityList.size()));
    }
}
