package org.profitsoft.random_creator_for_json_files;

import com.google.gson.Gson;
import org.profitsoft.model.Author;
import org.profitsoft.model.Book;
import org.profitsoft.model.Genre;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: Viacheslav Korbut
 * Date: 13.04.2024
 */
public class JSONFileCreator {
    private static final String[] FIRST_NAMES = {"George", "Jane", "William", "Charles", "Leo", "Agatha"};
    private static final String[] LAST_NAMES = {"Orwell", "Austen", "Shakespeare", "Dickens", "Tolstoy", "Christie"};

    public static void main(String[] args) {
        List<Book> books = generateRandomBooks(100000); // Generate 10 random books
        String filePath = "src/main/resources/json_files/books9.json"; // File path where the JSON will be saved

        // Serialize the list of books to JSON and write to file
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(books, writer);
            System.out.println("JSON file created successfully at path: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Book> generateRandomBooks(int numBooks) {
        List<Book> books = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numBooks; i++) {
            String title = "Book " + (i + 1);
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            int yearPublished = 1700 + random.nextInt(321); // Random year between 1700 and 2020
            Genre[] genres = getRandomGenres(random.nextInt(3) + 1); // Random number of genres (1 to 3)

            Author author = new Author(firstName, lastName);
            Book book = new Book(title, author, yearPublished, List.of(genres));
            books.add(book);
        }

        return books;
    }

    private static Genre[] getRandomGenres(int numGenres) {
        Random random = new Random();
        Genre[] allGenres = Genre.values();
        List<Genre> chosenGenres = new ArrayList<>();

        for (int i = 0; i < numGenres; i++) {
            Genre genre = allGenres[random.nextInt(allGenres.length)];
            chosenGenres.add(genre);
        }

        return chosenGenres.toArray(new Genre[0]);
    }

}
