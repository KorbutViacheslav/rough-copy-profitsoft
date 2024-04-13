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

/**
 * EN: Utility class for parsing JSON files containing information about books.
 * Provides method for asynchronously parsing JSON files in a specified folder.
 * <p>
 * UA: Утилітарний клас для парсингу JSON файлів, що містять інформацію про книги.
 * Надає метод для асинхронного парсингу JSON файлів у вказаній папці.
 * <p>
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class JSONFileParser {
    /**
     * EN: The number of threads used for parallel parsing of JSON files.
     * UA: Кількість потоків, що використовуються для паралельного парсингу JSON файлів.
     */
    private static final int NUM_THREADS = 8;

    private static ExecutorService executor;

    /**
     * EN: Private constructor to prevent instantiation of the JSONFileParser class.
     * This class is designed as a utility class with only static methods.
     * <p>
     * UA: Приватний конструктор для запобігання створення екземпляра класу JSONFileParser.
     * Цей клас розроблений як утилітарний клас і містить лише статичні методи.
     */
    private JSONFileParser() {
    }

    /**
     * EN: Parses all JSON files containing information about books in the specified folder asynchronously.
     * @param folderPath The path to the folder containing JSON files.
     * @return A list of books parsed from the JSON files.
     * @throws RuntimeException if an error occurs while reading the folder or parsing the files.
     *
     * UA: Парсить всі JSON файли, які містять інформацію про книги, у вказаній папці асинхронно.
     * @param folderPath Шлях до папки з JSON файлами.
     * @return Список книг, які були розібрані з JSON файлів.
     * @throws RuntimeException у разі помилки читання папки або парсингу файлів.
     */
    public static List<Book> parseBooksFromFolder(String folderPath) {
        //EN: Initialize a fixed thread pool executor with a specified number of threads
        //UA: Ініціалізуємо виконавця з фіксованою кількістю потоків
        executor = Executors.newFixedThreadPool(NUM_THREADS);

        try (var pathStream = Files.list(Path.of(folderPath))) {
            return pathStream
                    //EN: Filter the stream to include only JSON files
                    //UA: Фільтруємо потік, щоб включити тільки JSON файли
                    .filter(JSONFileParser::isJSONFile)
                    //EN: Map each JSON file path to a CompletableFuture representing the asynchronous parsing operation
                    //UA: Відображаємо кожний шлях до JSON файлу на CompletableFuture, що представляє асинхронну операцію розбору
                    .map(path -> JSONFileParser.parseJsonFilesAsync(path, executor))
                    //EN: Join each CompletableFuture to wait for its completion and get the parsed list of books
                    //UA: Об'єднуємо кожний CompletableFuture, щоб зачекати на його завершення та отримати розібраний список книг
                    .map(CompletableFuture::join)
                    //EN: Flatten the list of lists into a single list of books
                    //UA: Розгортаємо список списків у один список книг
                    .flatMap(List::stream)
                    //EN: Collect the books into a list
                    //UA: Збираємо книги у список
                    .toList();
        } catch (IOException e) {
            //EN: If an IOException or JsonSyntaxException occurs, print an error message and return an empty list
            //UA: Якщо виникає IOException або JsonSyntaxException, виводимо повідомлення про помилку та повертаємо пустий список
            throw new RuntimeException("Failed to read folder: " + folderPath, e);
        } finally {
            //EN: Shut down the executor to release its resources
            //UA: Зупиняємо виконавця для звільнення його ресурсів
            executor.shutdown();
        }
    }

    /**
     * EN: Asynchronously parses a JSON file containing information about books.
     * @param path The path to the JSON file.
     * @param executor The executor object to use for asynchronous execution.
     * @return A CompletableFuture representing the parsing operation.
     *
     * UA: Асинхронно розбирає JSON файл, який містить інформацію про книги.
     * @param path Шлях до JSON файлу.
     * @param executor Об'єкт виконавця для асинхронного виконання.
     * @return CompletableFuture, який представляє операцію розбору.
     */
    private static CompletableFuture<List<Book>> parseJsonFilesAsync(Path path, Executor executor) {
        return CompletableFuture.supplyAsync(() -> parseBookFromFile(path), executor);
    }

    /**
     * EN: Parses a JSON file containing information about books synchronously.
     * @param path The path to the JSON file.
     * @return A list of books parsed from the JSON file.
     *
     * UA: Синхронно розбирає JSON файл, який містить інформацію про книги.
     * @param path Шлях до JSON файлу.
     * @return Список книг, які були розібрані з JSON файлу.
     */
    private static List<Book> parseBookFromFile(Path path) {
        //EN: Deserialize the JSON content of the file into a list of books using Gson library
        //UA: Десеріалізуємо JSON вміст файлу у список книг за допомогою бібліотеки Gson
        Gson gson = new Gson();
        try (var br = Files.newBufferedReader(path)) {
            return gson.fromJson(br, new TypeToken<List<Book>>() {
            }.getType());
        } catch (IOException | JsonSyntaxException e) {
            //EN: If an IOException or JsonSyntaxException occurs, print an error message and return an empty list
            //UA: Якщо виникає IOException або JsonSyntaxException, виводимо повідомлення про помилку та повертаємо пустий список
            System.err.println("Failed to parse file: " + path);
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * EN: Checks whether the given path represents a JSON file based on its extension.
     * @param path The path to check.
     * @return {@code true} if the file represented by the path has a ".json" extension, {@code false} otherwise.
     *
     * UA: Перевіряє, чи представляє вказаний шлях JSON файл за його розширенням.
     * @param path Шлях для перевірки.
     * @return {@code true}, якщо файл, що представляється шляхом, має розширення ".json", {@code false} в іншому випадку.
     */
    private static boolean isJSONFile(Path path) {
        return path.getFileName().toString().endsWith(".json");
    }

    public static boolean isExecutorShutdown() {
        return executor == null || executor.isShutdown();
    }
}
