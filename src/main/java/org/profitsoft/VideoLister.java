package org.profitsoft;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class VideoLister {
    private static final DecimalFormat durationFormatter = new DecimalFormat("#.##");
    private static final Pattern dirNumberPattern = Pattern.compile("(\\d+) - ");

    public static void main(String[] args) {
        String rootDir = "E:\\JAVA\\LESSONS\\[Udemy, Bogdan Stashchuk] React - Полный Курс по React и Redux";
        Map<String, Long> directoriesDurations = new LinkedHashMap<>();

        listVideos(new File(rootDir), directoriesDurations);

        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(directoriesDurations.entrySet());
        sortedEntries.sort(Comparator.comparingInt(e -> getDirNumber(e.getKey())));

        System.out.println("Directories with video durations (sorted by directory number):");
        for (Map.Entry<String, Long> entry : sortedEntries) {
            String dir = entry.getKey();
            long duration = entry.getValue();
            System.out.println(dir + ": " + formatDuration(duration));
        }

        long totalDuration = 0;
        for (long duration : directoriesDurations.values()) {
            totalDuration += duration;
        }
        System.out.println("\nTotal duration for all videos: " + formatDuration(totalDuration));
    }

    private static void listVideos(File dir, Map<String, Long> directoriesDurations) {
        File[] files = dir.listFiles();
        if (files != null) {
            long subdirDuration = 0;
            for (File file : files) {
                if (file.isDirectory()) {
                    listVideos(file, directoriesDurations);
                } else if (file.getName().endsWith(".mp4") || file.getName().endsWith(".avi") || file.getName().endsWith(".mkv")) {
                    try {
                        EmbeddedMediaPlayer mediaPlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();
                        mediaPlayer.playMedia(file.getAbsolutePath());
                        long duration = mediaPlayer.getLength();
                        subdirDuration += duration / 1000000000; // Конвертація з наносекунд у секунди
                        mediaPlayer.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (subdirDuration > 0) {
                directoriesDurations.put(dir.getAbsolutePath(), subdirDuration);
            }
        }
    }

    private static int getDirNumber(String dirPath) {
        Matcher matcher = dirNumberPattern.matcher(dirPath);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return Integer.MAX_VALUE;
    }

    private static String formatDuration(long durationInSeconds) {
        long hours = durationInSeconds / 3600;
        long minutes = (durationInSeconds % 3600) / 60;
        long seconds = durationInSeconds % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }
}