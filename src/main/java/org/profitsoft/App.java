package org.profitsoft;

import org.profitsoft.model.Book;
import org.profitsoft.service.JSONFileParser;
import org.profitsoft.service.StatisticsCalculator;
import org.profitsoft.service.XmlWriter;
import org.profitsoft.util.StatisticsFileNameGenerator;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // JSONFileParser back list of book from JSON files in path
        List<Book> bookList = JSONFileParser.parseBooksFromFolder(StatisticsFileNameGenerator.PATH_TO_JSON_FILES);
        //bookList.forEach(System.out::println);

        // StatisticsCalculator back map of attribute and count from book list
        Map<String, Integer> stringIntegerMap = StatisticsCalculator.getStatistic("author", bookList);
        //stringIntegerMap.entrySet().forEach(System.out::println);

        //Write XML file with statistic from StatisticsCalculator
        XmlWriter.writeXMLFile(stringIntegerMap, "author");

    }
}
