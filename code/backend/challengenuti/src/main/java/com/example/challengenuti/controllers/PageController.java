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

import java.util.ArrayList;
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
        return "formulario";
    }

    @GetMapping("/desafio/formulario_busca")
    public String formBusca(){
        return "formulario_busca";
    }

    @GetMapping("/desafio/identificar_tags")
    public String save(@RequestParam List<String> urls, Model model){
        PageRequest pageRequest = new PageRequest();
        pageRequest.getUrls().addAll(urls);
        List<PageDTO> result = pageService.indentificarTags(pageRequest);
        model.addAttribute("pages", result);
        return "indetificar_tags";
    }

    @GetMapping("/desafio/buscar_tags_por_url")
    public String save(@RequestParam String url, Model model){
        PageDTO result = pageService.buscarTagsPorUrl(url);
        List<PageDTO> list = new ArrayList<>();
        list.add(result);
        model.addAttribute("pages", list);
        return "buscar_tags";
    }

    @GetMapping("/desafio/home")
    public String home(){
        return "home";
    }

}
