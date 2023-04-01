package com.book.domain.tag;

import com.book.domain.MyBook.MyBook;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class MyBookTag {

    @Id
    @GeneratedValue
    @Column(name = "myBookTag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myBook_id")
    private MyBook myBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void delete(){
        this.tag.deleteMyBookTag(this);
        this.myBook.deleteTag(this);
    }
}
