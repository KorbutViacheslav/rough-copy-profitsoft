package org.profitsoft.service;

import org.profitsoft.model.Book;

/**
 * Author: Viacheslav Korbut
 * Date: 08.04.2024
 */
public class FieldValueRetriever {
    public static Object getFieldValueByAttribute(Book book, String attribute) {
        return switch (attribute) {
            case "title" -> book.getTitle();
            case "author" -> book.getAuthor();
            case "yearPublished" -> book.getYearPublished();
            case "genre" -> book.getGenre();
            default -> throw new IllegalArgumentException("Invalid attribute - " + attribute);
        };
    }
}
