package org.profitsoft.util;

/**
 * Author: Viacheslav Korbut
 * Date: 08.04.2024
 */
public class DirectoryUtil {
    private final static String RESULTS_DIRECTORY = "src/main/resources/statistic_results/";
    private final static String PART_OF_FILE_NAME = "statistics_by_";
    private final static String TEMPLATE_POSTFIX_XML = "xml";

    public static String getXMLFileName(String attribute) {
        var stringBuilder = new StringBuilder(RESULTS_DIRECTORY)
                .append(PART_OF_FILE_NAME)
                .append(attribute)
                .append(TEMPLATE_POSTFIX_XML);
        return stringBuilder.toString();
    }

}
