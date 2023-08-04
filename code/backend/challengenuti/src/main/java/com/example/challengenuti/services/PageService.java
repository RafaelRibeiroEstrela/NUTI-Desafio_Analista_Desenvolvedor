package com.example.challengenuti.services;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.requests.PageRequest;

import java.util.List;

public interface PageService {

    List<PageDTO> indentificarTags(PageRequest pageRequest);
    PageDTO buscarTagsPorUrl(String url);
}
