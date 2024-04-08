package org.profitsoft.service;

import org.profitsoft.model.Book;
import org.profitsoft.model.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class StatisticsCalculator {
    private StatisticsCalculator() {
    }

    public static Map<String, Integer> getStatistic(String attribute, List<Book> bookList) {
        Map<String, Integer> statistics = new HashMap<>();

        for (Book book : bookList) {
            switch (attribute) {
                case "title" -> statistics.put(book.getTitle(), statistics.getOrDefault(book.getTitle(), 0) + 1);
                case "yearPublished" ->
                        statistics.put(book.getYearPublished().toString(), statistics.getOrDefault(book.getYearPublished().toString(), 0) + 1);

                case "author" -> {
                    String authorNameAndLastName = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
                    statistics.put(authorNameAndLastName, statistics.getOrDefault(authorNameAndLastName, 0) + 1);
                }
                case "genre" -> {
                    List<String> genreList = new ArrayList<>();
                    for (Genre genre : book.getGenre()) {
                        genreList.add(genre.name());
                    }
                    for (String genre : genreList) {
                        statistics.put(genre, statistics.getOrDefault(genre, 0) + 1);
                    }
                }
                default -> throw new IllegalArgumentException("Invalid attribute - " + attribute);
            }
        }

        return statistics;
    }

    //Another method to get statistic. This method I used .compute() to add title and count.
    /*public static Map<String, Integer> getStatistics(String attribute, List<Book> bookList) {
        Map<String, Integer> statistics = new HashMap<>();

        for (Book book : bookList) {
            switch (attribute) {
                case "title" -> statistics.compute(book.getTitle(), (key, value) -> value == null ? 1 : value + 1);
                case "yearPublished" ->
                        statistics.compute(book.getYearPublished().toString(), (key, value) -> value == null ? 1 : value + 1);
                case "author" ->
                        statistics.compute(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(), (key, value) -> value == null ? 1 : value + 1);
                case "genre" -> {
                    book.getGenre().forEach(genre -> statistics.compute(genre.name(), (key, value) -> value == null ? 1 : value + 1));
                }
                default -> throw new IllegalArgumentException("Invalid attribute - " + attribute);
            }
        }

        return statistics;
    }*/
}
