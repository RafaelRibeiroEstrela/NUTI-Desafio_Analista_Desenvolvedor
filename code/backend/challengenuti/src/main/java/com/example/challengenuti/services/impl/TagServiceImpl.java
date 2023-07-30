package com.example.challengenuti.services.impl;

import com.example.challengenuti.models.Tag;
import com.example.challengenuti.services.TagService;
import com.example.challengenuti.services.exceptions.FaildConnectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Override
    public List<Tag> verifyTag(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.info("Falha ao conectar na url {} ", e.getMessage());
            throw new FaildConnectionException("Falha ao conectar na url " + url);
        }
        Elements allTags = doc.getAllElements();
        List<Tag> tags = new ArrayList<>();
        for (Element element : allTags) {
            Elements list = doc.select(element.tag().getName());
            Tag tag = new Tag();
            tag.setName(element.tag().getName());
            tag.setCount(list.size());
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        return tags;
    }
}
