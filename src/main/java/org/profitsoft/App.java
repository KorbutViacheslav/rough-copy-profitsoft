package org.profitsoft;

import org.profitsoft.model.Book;
import org.profitsoft.service.JSONFileParser;
import org.profitsoft.util.StatisticsCalculator;
import org.profitsoft.service.StatisticsXmlWriter;
import org.profitsoft.util.StatisticsFileNameGenerator;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
/*        String pathToJSONFiles = args[0];
        String attributeForStatistics = args[1];*/

        String attribute = "title";
        String pathToFiles = StatisticsFileNameGenerator.PATH_TO_JSON_FILES;

        long startTime = System.currentTimeMillis();
        // JSONFileParser back list of book from JSON files in path
        List<Book> bookList = JSONFileParser.parseBooksFromFolder(pathToFiles);

        // StatisticsCalculator back map of attribute and count from book list
        Map<String, Integer> stringIntegerMap = StatisticsCalculator.getStatistic(attribute, bookList);

        //Write XML file with statistic from StatisticsCalculator
        StatisticsXmlWriter.writeXMLFile(stringIntegerMap, attribute);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time taken: " + duration + " milliseconds with "+ JSONFileParser.getNumberThreads()+" threads.");

    }
}
