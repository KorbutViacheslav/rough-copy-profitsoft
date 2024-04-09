package org.profitsoft.util;

/**
 * EN: Utility class for generating file names for statistics.
 * This class provides a convenient way to generate file names for statistics in XML format based on statistical attributes.
 * The attribute is used to construct the file name to reflect the content of the statistics.
 * For example, for the "genre" attribute, the file name "statistics_by_genre.xml" is generated.
 * All file paths and constants related to directories are specified in this class as static fields.
 * <p>
 * UA: Утилітарний клас для генерації імен файлів для статистики.
 * Цей клас забезпечує зручний спосіб генерації імен файлів для статистики у форматі XML на основі атрибутів статистики.
 * Атрибут використовується для побудови імені файлу, щоб відображати вміст статистики.
 * Наприклад, для атрибуту "genre" генерується ім'я файлу "statistics_by_genre.xml".
 * Всі шляхи до файлів і константи, пов'язані з директоріями, задані у цьому класі як статичні поля.
 * <p>
 * Author: Viacheslav Korbut
 * Date: 08.04.2024
 */
public class StatisticsFileNameGenerator {
    private final static String RESULTS_DIRECTORY = "src/main/resources/statistic_results/";
    private final static String PART_OF_FILE_NAME = "statistics_by_";
    private final static String TEMPLATE_POSTFIX_XML = ".xml";
    public final static String PATH_TO_JSON_FILES = "src/main/resources/json_files/";

    /**
     * EN: Generates an XML file name for the given statistics attribute.
     *
     * @param attribute The attribute of the statistics used in the file name
     * @return The XML file name in the format "statistics_by_{attribute}.xml"
     *
     * UA: Генерує ім'я XML-файлу для заданого атрибуту статистики.
     *
     * @param attribute Атрибут статистики, який буде використаний у назві файлу
     * @return Ім'я XML-файлу у форматі "statistics_by_{attribute}.xml"
     */
    public static String getXMLFileName(String attribute) {
        var stringBuilder = new StringBuilder(RESULTS_DIRECTORY)
                .append(PART_OF_FILE_NAME)
                .append(attribute)
                .append(TEMPLATE_POSTFIX_XML);
        return stringBuilder.toString();
    }


}
