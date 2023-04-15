package com.book.service.book;

import com.book.domain.MyBook.MyBook;
import com.book.domain.MyBook.dto.request.MyBookCreateDto;
import com.book.domain.book.Book;
import com.book.domain.book.dto.request.BookResDto;
import com.book.repository.book.BookRepository;
import com.book.repository.mybook.MyBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final SearchService searchService;
    private final MyBookRepository myBookRepository;

    public Book createBook(MyBookCreateDto bookDto){
        return bookRepository.save(bookDto.toBook());
    }

    public Book getBook(MyBookCreateDto bookDto){
        return bookRepository.findByIsbn(bookDto.getIsbn()).orElse(createBook(bookDto));
    }

    public BookResDto getBook(String isbn){
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        BookResDto bookResDto = null;
        if(book.isEmpty()){
            bookResDto = searchService.searchDetail(isbn);
        } else {
            bookResDto = book.get().toResDto();
        }
        return addMyBook(bookResDto);
    }

    public BookResDto addMyBook(BookResDto bookResDto){
        List<MyBook> myBooks = myBookRepository.findByIsbn(bookResDto.getIsbn());
        for (MyBook myBook : myBooks) {
            bookResDto.addBook(myBook.toResDto());
        }
        return bookResDto;
    }


}
