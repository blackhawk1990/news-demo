package com.example.newsdemo.restclient;

import com.example.newsdemo.dto.NewsList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsClient {

    @Value("${news.api.url}")
    private String mainApiUrl;

    @Value("${news.api.key}")
    private String apiKey;

    public NewsList getTopBusinessNews(String countryCode) {
        return this.getTopNewses(countryCode, "business");
    }

    private NewsList getTopNewses(String countryCode, String category) {
        String apiActionUrl = "/top-headlines?country=" + countryCode + "&category=" + category + "&apiKey=" + this.apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewsList> response = restTemplate.getForEntity(this.mainApiUrl + apiActionUrl, NewsList.class);

        return response.getBody();
    }
}
