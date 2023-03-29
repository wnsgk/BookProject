package com.book.repository;

import com.book.domain.tag.Tag;
import com.book.domain.tag.UserBookTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBookTagRepository extends JpaRepository<UserBookTag, Long> {

    @Query("select t from UserBookTag t join fetch t.userBook where t.tag_id == :id")
    List<UserBookTag> findAllUserBook(@Param(value = "id") Long tagId);

}
