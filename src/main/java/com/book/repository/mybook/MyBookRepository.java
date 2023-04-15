package com.book.repository.mybook;

import com.book.domain.MyBook.MyBook;
import com.book.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {

    Optional<MyBook> findByUserIdAndBookId(Long userId, Long bookId);

    List<MyBook> findAllByUserId(Long userId);

    List<MyBook> findByBookId(Long bookId);


    boolean existsByUserAndIsbn(User user, String isbn);

    List<MyBook> findByIsbn(String isbn);
}
