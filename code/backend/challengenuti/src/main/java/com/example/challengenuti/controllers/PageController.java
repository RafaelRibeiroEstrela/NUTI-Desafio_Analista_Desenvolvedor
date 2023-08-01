package com.example.challengenuti.controllers;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.requests.PageRequest;
import com.example.challengenuti.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/desafio")
public class PageController {

    private Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    private final PageService pageService;

    public PageController(PageService pageService){
        this.pageService = pageService;
    }

    @PostMapping("/identificar_tags")
    public ResponseEntity<List<PageDTO>> save1(@RequestBody PageRequest pageRequest){
        LOGGER.info("INICIANDO save COM {}", pageRequest);
        List<PageDTO> result = pageService.save(pageRequest);
        LOGGER.info("FINALIZANDO save COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
