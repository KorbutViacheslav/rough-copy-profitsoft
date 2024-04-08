package org.profitsoft.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.profitsoft.model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class JSONFileParser {
    private JSONFileParser() {
    }

    public static List<Book> getAllFiles(String folderPath) {
        List<Book> bookList = new ArrayList<>();
        try (Stream<Path> pathStream = Files.list(Path.of(folderPath))) {
            pathStream.forEachOrdered(path -> {
                try {
                    if (isJSONFile(path)) {
                        bookList.addAll(parseBookFromFile(path));
                    } else {
                        System.err.println("Not a JSON file: " + path);
                    }
                } catch (IOException | JsonSyntaxException e) {
                    System.err.println("Failed to parse file: " + path);
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to read folder: " + folderPath);
            e.printStackTrace();
        }
        return bookList;
    }

    private static List<Book> parseBookFromFile(Path path) throws IOException {
        Gson gson = new Gson();
        List<Book> bookList;
        try (var br = Files.newBufferedReader(path)) {
            bookList = gson.fromJson(br, new TypeToken<List<Book>>() {
            }.getType());
        }
        return bookList;
    }

    private static boolean isJSONFile(Path path) {
        return path.getFileName().toString().endsWith(".json");
    }
}
