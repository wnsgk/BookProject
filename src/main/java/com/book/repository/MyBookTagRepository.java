package com.book.repository;

import com.book.domain.tag.MyBookTag;
import com.book.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyBookTagRepository extends JpaRepository<MyBookTag, Long> {

    @Query("select t from MyBookTag t join fetch t.myBook m where t.tag = :tag")
    List<MyBookTag> findAllMyBooks(@Param(value = "tag") Tag tag);


}
