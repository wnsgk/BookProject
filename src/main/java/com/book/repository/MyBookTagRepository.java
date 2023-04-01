package com.book.repository;

import com.book.domain.tag.MyBookTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyBookTagRepository extends JpaRepository<MyBookTag, Long> {

    @Query("select t from MyBookTag t join fetch t.myBook where t.tag = :id")
    List<MyBookTag> findMyBooks(@Param(value = "id") Long tagId);


}
