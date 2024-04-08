package org.profitsoft;

import com.google.gson.Gson;
import org.profitsoft.model.Author;
import org.profitsoft.model.Book;
import org.profitsoft.model.Genre;
import org.profitsoft.service.JSONFileParser;
import org.profitsoft.service.StatisticsCalculator;
import org.profitsoft.service.XmlWriter;
import org.profitsoft.util.DirectoryUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // JSONFileParser back list of book from JSON files in path
        List<Book> bookList = JSONFileParser.getAllFiles(DirectoryUtil.PATH_TO_JSON_FILES);
        //bookList.forEach(System.out::println);

        // StatisticsCalculator back map of attribute and count from book list
        Map<String, Integer> stringIntegerMap = StatisticsCalculator.getStatistic("author", bookList);
        //stringIntegerMap.entrySet().forEach(System.out::println);

        //Write XML file with statistic from StatisticsCalculator
        XmlWriter.writeXMLFile(stringIntegerMap, "author");


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
