package com.book.repository;

import com.book.domain.tag.Tag;
import com.book.domain.tag.UserBookTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByName(String name);
}
