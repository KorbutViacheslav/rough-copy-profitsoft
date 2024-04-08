package org.profitsoft.service;

import org.profitsoft.util.DirectoryUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Author: Viacheslav Korbut
 * Date: 04.04.2024
 */
public class XmlWriter {


    public static void writeXMLFile(Map<String, Integer> statisticMap, String fileName) {

        try (var bufferedWriter = new BufferedWriter(new FileWriter(DirectoryUtil.getXMLFileName(fileName)))) {
            bufferedWriter.write("<statistics>\n");
            for (Map.Entry<String, Integer> entry : statisticMap.entrySet()) {
                String attribute = entry.getKey();
                Integer count = entry.getValue();
                bufferedWriter.write("  <item>\n");
                bufferedWriter.write("    <value>" + attribute + "</value>\n");
                bufferedWriter.write("    <count>" + count + "</count>\n");
                bufferedWriter.write("  </item>\n");
            }
            bufferedWriter.write("</statistics>");
        } catch (IOException e) {
            System.err.println("An error occurred while writing the XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
