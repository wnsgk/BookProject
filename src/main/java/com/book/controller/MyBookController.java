package com.book.controller;

import com.book.config.auth.PrincipalDetails;
import com.book.domain.MyBook.*;
import com.book.service.BookService;
import com.book.service.MyBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mybook")
public class MyBookController {

    private final MyBookService myBookService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMyBook(@AuthenticationPrincipal PrincipalDetails userDetails,
                               MyBookCreateDto myBookDto) {
        myBookService.createMyBook(userDetails.getUser(), myBookDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/detail")
    public ResponseEntity<MyBook> getMyBook(@AuthenticationPrincipal PrincipalDetails userDetails,
                                              @RequestParam(name = "mybookid") Long id){
        myBookService.checkAccessPermission(id, userDetails.getUsername());
        return ResponseEntity.ok(myBookService.getMyBook(id));
    }

    @GetMapping("/shelf")
    public ResponseEntity<List<MyBookResDto>> myBookList(@RequestParam(name = "userid") Long userId){
        List<MyBookResDto> userBookList = myBookService.getMyBookList(userId);
        return ResponseEntity.ok(userBookList);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Void> update(@AuthenticationPrincipal PrincipalDetails userDetails,
                                       @RequestBody MyBookUpdateDto myBookDto,
                                       @PathVariable("id") Long id){

        myBookService.checkAccessPermission(id, userDetails.getUsername());
        myBookService.updateMyBook(id, myBookDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal PrincipalDetails userDetails,
                       @RequestParam Long id){

        myBookService.checkAccessPermission(id, userDetails.getUsername());
        myBookService.deleteMyBooks(id);

        return ResponseEntity.ok().build();
    }

}
