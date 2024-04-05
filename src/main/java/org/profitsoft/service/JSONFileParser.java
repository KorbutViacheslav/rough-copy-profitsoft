package org.profitsoft.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.profitsoft.model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class JSONFileParser {
    private final static Gson gson = new Gson();

    private JSONFileParser() {
    }

    public static List<Book> parseBookFromFile(String path) {
        List<Book> bookList = new ArrayList<>();
        try (var br = Files.newBufferedReader(Paths.get(path))) {
            List<Book> books = gson.fromJson(br, new TypeToken<List<Book>>() {
            }.getType());

            bookList.addAll(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
