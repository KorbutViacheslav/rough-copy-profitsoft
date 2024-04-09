package org.profitsoft.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.profitsoft.model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class JSONFileParser {
    private static final int NUM_THREADS = 8;

    private JSONFileParser() {
    }

    public static List<Book> getAllFiles(String folderPath) {
        var executor = Executors.newFixedThreadPool(NUM_THREADS);
        try (var pathStream = getPathStream(folderPath)) {
            return pathStream
                    .filter(JSONFileParser::isJSONFile)
                    .map(path -> JSONFileParser.parseJsonFilesAsync(path, executor))
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read folder: " + folderPath, e);
        } finally {
            executor.shutdown();
        }
    }

    private static Stream<Path> getPathStream(String folderPath) throws IOException {
        return Files.list(Path.of(folderPath));
    }

    private static CompletableFuture<List<Book>> parseJsonFilesAsync(Path path, Executor executor) {
        return CompletableFuture.supplyAsync(() -> parseBookFromFile(path), executor);
    }

    private static List<Book> parseBookFromFile(Path path) {
        Gson gson = new Gson();
        try (var br = Files.newBufferedReader(path)) {
            return gson.fromJson(br, new TypeToken<List<Book>>() {
            }.getType());
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Failed to parse file: " + path);
            e.printStackTrace();
            return List.of();
        }
    }

    private static boolean isJSONFile(Path path) {
        return path.getFileName().toString().endsWith(".json");
    }
}
