package com.example.challengenuti.requests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "urls=" + urls +
                '}';
    }
}
