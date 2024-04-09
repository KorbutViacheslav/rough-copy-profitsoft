package org.profitsoft.util;

/**
 * Утилітарний клас для роботи з файлами статистики.
 * Цей клас забезпечує зручний спосіб генерації імен файлів зі статистикою в форматі XML.
 * Він містить методи для створення імен файлів на основі атрибутів статистики.
 * Атрибут використовується для побудови імені файлу, щоб відображати вміст статистики.
 * Наприклад, для атрибуту "genre" генерується ім'я файлу "statistics_by_genre.xml".
 * Всі шляхи до файлів і константи, пов'язані з директоріями, задані у цьому класі як статичні поля.
 * Author: Viacheslav Korbut
 * Date: 08.04.2024
 */
public class StatisticsFileUtil {
    private final static String RESULTS_DIRECTORY = "src/main/resources/statistic_results/";
    private final static String PART_OF_FILE_NAME = "statistics_by_";
    private final static String TEMPLATE_POSTFIX_XML = ".xml";
    public final static String PATH_TO_JSON_FILES = "src/main/resources/json_files/";

    /**
     * Генерує ім'я XML-файлу для заданого атрибуту статистики.
     *
     * @param attribute атрибут статистики, який буде використаний у назві файлу
     * @return ім'я XML-файлу у форматі "statistics_by_{attribute}.xml"
     */
    public static String getXMLFileName(String attribute) {
        var stringBuilder = new StringBuilder(RESULTS_DIRECTORY)
                .append(PART_OF_FILE_NAME)
                .append(attribute)
                .append(TEMPLATE_POSTFIX_XML);
        return stringBuilder.toString();
    }


}
