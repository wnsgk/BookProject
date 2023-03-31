package com.book.controller;

import com.book.domain.book.SearchDto;
import com.book.domain.book.SearchResDto;
import com.book.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchDto> search(
            @RequestParam String keyword,
            @RequestParam int start) {
        return ResponseEntity.ok().body(searchService.search(keyword, start));
    }

    @GetMapping("/detail")
    public ResponseEntity<SearchResDto> searchDetail(@RequestParam String isbn) {
        return ResponseEntity.ok().body(searchService.searchDetail(isbn));
    }

}
