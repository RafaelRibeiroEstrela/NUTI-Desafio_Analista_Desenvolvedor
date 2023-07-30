package com.example.challengenuti.repositories;

import com.example.challengenuti.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findById(Long id);
    Optional<Page> findByUrl(String url);
}
