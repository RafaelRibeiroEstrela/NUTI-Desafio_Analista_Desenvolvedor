package com.example.challengenuti.services.impl;

import com.example.challengenuti.models.Tag;
import com.example.challengenuti.services.TagService;
import com.example.challengenuti.services.exceptions.FaildConnectionException;
import com.example.challengenuti.utils.TagUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Override
    public List<Tag> verifyTag1(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.info("Falha ao conectar na url {} ", e.getMessage());
            throw new FaildConnectionException("Falha ao conectar na url " + url);
        }

        //Obtem todos os elementos da página html
        Elements allTags = doc.getAllElements();
        List<Tag> tags = new ArrayList<>();
        for (Element element : allTags) {

            //Obtem elementos de uma tag específica
            Elements list = doc.select(element.tag().getName());
            Tag tag = new Tag();

            //Obtem o nome da tag
            tag.setName(element.tag().getName());

            //Obtem o numero de vezes que a tag aparece
            tag.setCount(list.size());
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        return tags;
    }

    @Override
    public List<Tag> verifyTag2(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.info("Falha ao conectar na url {} ", e.getMessage());
            throw new FaildConnectionException("Falha ao conectar na url " + url);
        }
        String html = doc.html();
        List<Tag> tags = new ArrayList<>();
        for (String obj : TagUtil.TAG_LIST){
            if (html.contains(obj)){
                int count = StringUtils.countMatches(html.toLowerCase(), obj.toLowerCase());
                Tag tag = new Tag();
                tag.setName(obj.replace("<", ""));
                tag.setCount(count);
                if (!tags.contains(tag)) {
                    tags.add(tag);
                }
            }
        }
        return tags;
    }
}
