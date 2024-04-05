package org.profitsoft.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.profitsoft.model.Book;
import org.profitsoft.model.Genre;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class JSONFileParser {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Genre.class, new GenreAdapter())
            .create();

    private JSONFileParser() {
    }

    public static List<Book> getBookFromFile(String directory) {
        List<Book> bookList = new ArrayList<>();


        return bookList;
    }

    public static List<Book> parseBookFromFile(String path) {
        List<Book> bookList = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            Type listType = new TypeToken<List<Book>>() {
            }.getType();
            bookList = gson.fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }
}
