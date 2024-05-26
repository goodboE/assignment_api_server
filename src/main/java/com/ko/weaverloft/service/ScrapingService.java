package com.ko.weaverloft.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapingService {

    public List<String> extractLinks(String url) {
        List<String> links = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements aTags = doc.select("a[href]");
            for (Element aTag : aTags) {
                String link = aTag.attr("abs:href");
                links.add(link);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return links;
    }
}
