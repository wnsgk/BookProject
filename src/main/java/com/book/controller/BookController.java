package com.book.controller;

import com.book.domain.book.BookResDto;
import com.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResDto> getBookInfo(@PathVariable("isbn") String isbn){
        BookResDto book = bookService.getBook(isbn);
        return ResponseEntity.ok(book);
    }

}
