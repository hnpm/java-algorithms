package com.example.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebCrawler {
    public static void discover(String url) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(url);
        while (!queue.isEmpty()) {
            String tempUrl = queue.remove();
            if (!visited.contains(tempUrl)) {
                System.out.println(tempUrl);
                visited.add(tempUrl);
                String html = getHtml(tempUrl);
                String regex = "https?://\\w+(\\.\\w+)+";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(html);
                while (matcher.find()) {
                    queue.add(matcher.group());
                }
            }
        }
    }

    private static String getHtml(String urlStr) {
        String html = "";
        try {
            URL url = new URL(urlStr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            html = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            System.out.println("Could not retrieve html string for the url: " + urlStr);
        }
        return html;
    }
}
