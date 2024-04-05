package org.profitsoft;

import com.google.gson.Gson;
import org.profitsoft.model.Author;
import org.profitsoft.model.Book;
import org.profitsoft.model.Genre;
import org.profitsoft.service.JSONFileParser;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Book> bookList = JSONFileParser.parseBookFromFile("src/main/resources/json_files/books.json");
        bookList.forEach(System.out::println);







//Training with object converting from JSON and back
        /*Book book = new Book();
        Author author = new Author();
        author.setFirstName("Slava");
        author.setLastName("Kor");
        book.setTitle("New book");
        book.setAuthor(author);
        book.setYearPublished(2024);
        List<Genre> genreList = List.of(Genre.ROMANCE,Genre.POLITICAL_FICTION);
        book.setGenre(genreList);

        Gson gson =new Gson();
        String s = gson.toJson(book);
        System.out.println(s);
        Book book1 = gson.fromJson(s, Book.class);
        System.out.println(book1);*/
    }
}
