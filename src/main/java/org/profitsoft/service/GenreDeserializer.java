package org.profitsoft.service;

import com.google.gson.*;
import org.profitsoft.model.Genre;

import java.lang.reflect.Type;

/**
 * Author: Viacheslav Korbut
 * Date: 05.04.2024
 */
public class GenreDeserializer implements JsonDeserializer<Genre> {
    @Override
    public Genre deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String genreString = json.getAsString();
        try {
            return Genre.valueOf(genreString.toUpperCase()); // Перетворюємо рядок у відповідне значення перерахування
        } catch (IllegalArgumentException e) {
            return null; // Якщо рядок не відповідає жодному значенню перерахування, можна повернути null або обробити помилку за необхідності
        }
    }
}

