package com.example.newsdemo;

import com.example.newsdemo.dto.News;
import com.example.newsdemo.dto.NewsList;
import com.example.newsdemo.restclient.NewsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class NewsDemoApplication implements CommandLineRunner {

    @Autowired
    private NewsClient client;

    public static void main(String[] args) {
        SpringApplication.run(NewsDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Loading data");
        List<News> newses = this.getTopNewses().getArticles();

        System.out.println("Saving to file");
        this.saveNewsFile(newses);
    }

    private NewsList getTopNewses() {
        return this.client.getTopBusinessNews("pl");
    }

    private void saveNewsFile(List<News> newses) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input news file location:");
            String fileLocation = reader.readLine();
            File file = new File(fileLocation);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : this.getMappedLines(newses)) {
                writer.append(line + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> getMappedLines (List<News> newses) {
        return newses.stream().map((News news) -> this.mapNewsFileLine(news)).collect(Collectors.toList());
    }

    private String mapNewsFileLine(News news) {
        return news.getTitle() + ":" + news.getDescription() + ":" + news.getAuthor();
    }
}
