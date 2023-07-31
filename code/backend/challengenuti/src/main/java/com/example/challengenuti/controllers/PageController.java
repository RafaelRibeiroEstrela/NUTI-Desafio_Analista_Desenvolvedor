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

    @GetMapping("/buscar_todos")
    public ResponseEntity<List<PageDTO>> findAll(){
        LOGGER.info("INICIANDO findAll");
        List<PageDTO> result = pageService.findAll();
        LOGGER.info("FINALIZANDO findAll COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageDTO> findById(@PathVariable Long id){
        LOGGER.info("INICIANDO findById COM id = {}", id);
        PageDTO result = pageService.findById(id);
        LOGGER.info("FINALIZANDO findById COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/buscar_por_url")
    public ResponseEntity<PageDTO> findByUrl(@RequestParam String url){
        LOGGER.info("INICIANDO findByUrl COM url = {}", url);
        PageDTO result = pageService.findByUrl(url);
        LOGGER.info("FINALIZANDO findByUrl COM {} }", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/implementacao_1/identificar_tags")
    public ResponseEntity<List<PageDTO>> save1(@RequestBody PageRequest pageRequest){
        LOGGER.info("INICIANDO save COM {}", pageRequest);
        List<PageDTO> result = pageService.save1(pageRequest);
        LOGGER.info("FINALIZANDO save COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/implementacao_2/identificar_tags")
    public ResponseEntity<List<PageDTO>> save2(@RequestBody PageRequest pageRequest){
        LOGGER.info("INICIANDO save COM {}", pageRequest);
        List<PageDTO> result = pageService.save2(pageRequest);
        LOGGER.info("FINALIZANDO save COM {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
