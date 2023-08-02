package com.example.challengenuti.controllers;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.requests.PageRequest;
import com.example.challengenuti.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {

    private Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    private final PageService pageService;

    public PageController(PageService pageService){
        this.pageService = pageService;
    }

    @GetMapping("/desafio/formulario")
    public String form(){
        return "form";
    }

    @GetMapping("/desafio/identificar_tags")
    public String save(@RequestParam List<String> urls, Model model){
        PageRequest pageRequest = new PageRequest();
        pageRequest.getUrls().addAll(urls);
        List<PageDTO> result = pageService.save(pageRequest);
        model.addAttribute("pages", result);
        return "pages";
    }

}
