package com.book.controller.tag;

import com.book.domain.MyBook.MyBook;
import com.book.service.tag.TagService;
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
    public ResponseEntity<List<MyBook>> search(@RequestParam("keyword") String keyword){
        List<MyBook> myBooks = tagService.searchTag(keyword);

        return ResponseEntity.ok(myBooks);
    }

}
