package com.book.service;

import com.book.domain.MyBook.*;
import com.book.domain.book.Book;
import com.book.domain.user.User;
import com.book.exception.InvalidReqBodyException;
import com.book.exception.user.UserNotFoundException;
import com.book.exception.book.BookNotFoundException;
import com.book.exception.book.DuplicateBookException;
import com.book.exception.book.UnAuthorizedAccess;
import com.book.repository.MyBookRepository;
import com.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyBookService {

    private final MyBookRepository myBookRepository;
    private final UserRepository userRepository;
    private final TagService tagService;
    private final BookService bookService;


    @Transactional
    public MyBook createMyBook(User user, MyBookCreateDto myBookDto){
        checkDuplicateBook(user, myBookDto.getIsbn());
        validateDate(myBookDto.getStartDate(), myBookDto.getEndDate());

        Book book = bookService.getBook(myBookDto);
        MyBook myBook = myBookDto.create(book, user);
        myBookRepository.save(myBook);
        for (String tag: myBookDto.getTags()) {
            tagService.addTag(tag, myBook);
        }
        return myBook;
    }

    @Transactional(readOnly = true)
    public MyBook getMyBook(Long id){
        return myBookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<MyBookResDto> getMyBookList(Long userId){
        return myBookRepository.findAllByUserId(userId).stream().map(b -> b.toResDto())
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateMyBook(Long userId, MyBookUpdateDto myBookDto){
        MyBook myBook = myBookRepository.findById(userId).orElseThrow(BookNotFoundException::new);
        myBook.update(myBookDto);
        tagService.updateTag(myBook, myBookDto.getTags());
    }

    //미완성
    @Transactional
    public void deleteMyBooks(Long id){
        myBookRepository.deleteById(id);
    }

    public void checkAccessPermission(Long userBookId, String email){
        MyBook myBook = myBookRepository.findById(userBookId).orElseThrow(BookNotFoundException::new);
        if(!myBook.isOpen()){
            User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
            if(myBook.getUser() != user){
                throw new UnAuthorizedAccess("공개되지 않은 책입니다.");
            }
        }
    }

    private void checkDuplicateBook(User user, String isbn) {
        if(myBookRepository.existsByUserAndIsbn(user, isbn)){
            throw new DuplicateBookException("이미 등록된 책입니다.");
        }
    }

    private void validateDate(LocalDate start, LocalDate end) {
        if (end.isBefore(start))
            throw new InvalidReqBodyException("date = " + start + " < " + end);
    }
}
