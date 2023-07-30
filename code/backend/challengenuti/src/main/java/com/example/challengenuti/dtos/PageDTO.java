package com.example.challengenuti.dtos;

import com.example.challengenuti.models.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;
    private List<TagDTO> tags = new ArrayList<>();

    public PageDTO(){

    }

    public PageDTO(Page page){
        this.url = page.getUrl();
        this.tags.addAll(page.getTags().stream()
                .map(obj -> new TagDTO(obj))
                .collect(Collectors.toList()));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                ", url='" + url + '\'' +
                ", tags=" + tags +
                '}';
    }
}
