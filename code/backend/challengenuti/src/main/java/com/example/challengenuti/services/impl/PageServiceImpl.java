package com.example.challengenuti.services.impl;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.models.Page;
import com.example.challengenuti.models.Tag;
import com.example.challengenuti.repositories.PageRepository;
import com.example.challengenuti.requests.PageRequest;
import com.example.challengenuti.services.PageService;
import com.example.challengenuti.services.TagService;
import com.example.challengenuti.services.exceptions.RequestInvalidException;
import com.example.challengenuti.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
                LOGGER.info("Análise já existe no banco de dados. Limpando registros antigos.");
                pageRepository.deleteById(optional.get().getId());
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

    @Override
    public PageDTO findByUrl(String url) {
        Optional<Page> optional = pageRepository.findByUrl(url);
        if (optional.isPresent()){
            return new PageDTO(optional.get());
        }
        throw new ResourceNotFoundException("Não foi encontrado uma análise com a URL informada.");
    }

}
