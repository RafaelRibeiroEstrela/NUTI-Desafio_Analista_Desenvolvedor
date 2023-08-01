package com.example.challengenuti.controllers;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.requests.PageRequest;
import com.example.challengenuti.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PageDTO>> save(@RequestBody PageRequest pageRequest){
        LOGGER.info("INICIANDO save COM {}", pageRequest);
        List<PageDTO> result = pageService.save(pageRequest);
        LOGGER.info("FINALIZANDO save COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/buscar_tags_por_url")
    public ResponseEntity<PageDTO> findByUrl(@RequestParam String url){
        LOGGER.info("INICIANDO findByUrl COM url={}", url);
        PageDTO result = pageService.findByUrl(url);
        LOGGER.info("FINALIZANDO findByUrl COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
