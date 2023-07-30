package com.example.challengenuti.services;

import com.example.challengenuti.dtos.PageDTO;
import com.example.challengenuti.requests.PageRequest;

import java.util.List;

public interface PageService {

    PageDTO findById(Long id);
    PageDTO findByUrl(String url);
    List<PageDTO> findAll();
    List<PageDTO> save(PageRequest pageRequest);
}
