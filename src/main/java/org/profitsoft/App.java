package org.profitsoft;

import org.profitsoft.model.Book;
import org.profitsoft.service.JSONFileParser;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        List<Book> bookList = JSONFileParser.parseBookFromFile("E:\\JAVA\\projekt\\rough-copy-profitsoft\\src\\main\\resources\\json_files\\books.json");
       bookList.forEach(System.out::println);
        System.out.println( "Hello World!" );
    }
}
