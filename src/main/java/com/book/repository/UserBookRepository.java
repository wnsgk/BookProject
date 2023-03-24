package com.book.repository;

import com.book.domain.book.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    Optional<UserBook> findByUserIdAndBookId(Long userId, Long bookId);

    List<UserBook> findAllByUserId(Long userId);

    List<UserBook> findByBookId(Long bookId);
}
