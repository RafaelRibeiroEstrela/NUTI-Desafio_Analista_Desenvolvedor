package com.example.challengenuti.repositories;

import com.example.challengenuti.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {

    @Query("SELECT p from Page p join Tag t on p.id = t.page.id where p.url = ?1 ")
    Optional<Page> findByUrl(String url);
}
