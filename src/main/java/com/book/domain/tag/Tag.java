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
    private List<UserBookTag> userBookTags = new ArrayList<>();

    @Builder
    public Tag(String name){
        this.name = name;
    }

    //== 연관관계 메서드 ==//
    public void addTag(UserBookTag userBookTag){
        this.userBookTags.add(userBookTag);
    }
    public boolean deleteUserBookTag(UserBookTag userBookTag) {
        this.userBookTags.remove(userBookTag);
        if (this.userBookTags.isEmpty()) {
            return true;
        }
        return false;
    }
}
