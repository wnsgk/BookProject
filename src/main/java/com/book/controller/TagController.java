package com.book.controller;

import com.book.domain.book.UserBook;
import com.book.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<UserBook>> search(@RequestParam("keyword") String keyword){
        List<UserBook> userBooks = tagService.searchTag(keyword);

        return ResponseEntity.ok(userBooks);
    }

}
