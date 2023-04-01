package com.book.domain.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<MyBookTag> myBookTags = new ArrayList<>();

    @Builder
    public Tag(String name){
        this.name = name;
    }

    //== 연관관계 메서드 ==//
    public void addTag(MyBookTag myBookTag){
        this.myBookTags.add(myBookTag);
    }

    public boolean deleteMyBookTag(MyBookTag myBookTag) {
        this.myBookTags.remove(myBookTag);
        if (this.myBookTags.isEmpty()) {
            return true;
        }
        return false;
    }
}
