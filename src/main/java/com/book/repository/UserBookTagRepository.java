package com.book.repository;

import com.book.domain.tag.Tag;
import com.book.domain.tag.UserBookTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookTagRepository extends JpaRepository<UserBookTag, Long> {

    public List<UserBookTag> findByTag(Tag tag);
}
