package org.profitsoft.service;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.profitsoft.model.Genre;

import java.io.IOException;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class GenreAdapter extends TypeAdapter<Genre> {

    @Override
    public void write(JsonWriter out, Genre value) throws IOException {
        out.value(value.name());
    }

    @Override
    public Genre read(JsonReader in) throws IOException {
        return Genre.valueOf(in.nextString());
    }
}

