package com.example.challengenuti.services.impl;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.models.Page;
import com.example.challengenuti.models.Tag;
import com.example.challengenuti.repositories.PageRepository;
import com.example.challengenuti.requests.PageRequest;
import com.example.challengenuti.services.PageService;
import com.example.challengenuti.services.TagService;
import com.example.challengenuti.services.exceptions.DatabaseException;
import com.example.challengenuti.services.exceptions.RequestInvalidException;
import com.example.challengenuti.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements PageService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class);
    private final PageRepository pageRepository;
    private final TagService tagService;

    public PageServiceImpl(PageRepository pageRepository, TagService tagService){
        this.pageRepository = pageRepository;
        this.tagService = tagService;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO findById(Long id) {
        LOGGER.info("Buscando por id");
        Optional<Page> optional = pageRepository.findById(id);
        if (optional.isPresent()){
            Page page = optional.get();
            return new PageDTO(page);
        }
        LOGGER.info("ResourceNotFoundException findById");
        throw new ResourceNotFoundException("Nenhuma página foi encontrada.");
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO findByUrl(String url) {
        LOGGER.info("Buscando por url");
        Optional<Page> optional = pageRepository.findByUrl(url);
        if (optional.isPresent()){
            Page page = optional.get();
            return new PageDTO(page);
        }
        LOGGER.info("ResourceNotFoundException findByUrl");
        throw new ResourceNotFoundException("Nenhuma página foi encontrada.");
    }

    @Transactional(readOnly = true)
    @Override
    public List<PageDTO> findAll() {
        LOGGER.info("Buscando todos");
        List<Page> pages = pageRepository.findAll();
        if (pages.isEmpty()){
            LOGGER.info("ResourceNotFoundException findAll");
            throw new ResourceNotFoundException("Nenhuma página foi encontrada.");
        }
        return pages.stream()
                .map(obj -> new PageDTO(obj))
                .collect(Collectors.toList());
    }

    @Override
    public List<PageDTO> save(PageRequest pageRequest) {
        LOGGER.info("Identificando tags");
        if (pageRequest.getUrls() == null || pageRequest.getUrls().isEmpty()){
            LOGGER.info("RequestInvalidException save");
            throw new RequestInvalidException("A lista de urls deve possui ao menos um elemento.");
        }
        List<Page> pages = new ArrayList<>();
        pageRequest.getUrls().forEach(url -> {
            Optional<Page> optional = pageRepository.findByUrl(url);
            if (optional.isPresent()){
                LOGGER.info("DatabaseException save");
                throw new DatabaseException("Já existe uma análise da url " + url + " .");
            }
            List<Tag> tags = tagService.verifyTag(url);
            Page page = new Page();
            page.setUrl(url);
            page.getTags().addAll(tags);
            tags.forEach(obj -> obj.setPage(page));
            pages.add(page);
        });
        LOGGER.info("Salvando tags identificadas");
        return pageRepository.saveAll(pages).stream()
                .map(obj -> new PageDTO(obj))
                .collect(Collectors.toList());
    }

}
