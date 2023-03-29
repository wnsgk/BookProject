package com.book.domain.tag;

import com.book.domain.book.UserBook;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class UserBookTag {

    @Id
    @GeneratedValue
    @Column(name = "userBookTag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userBook_id")
    private UserBook userBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void delete(){
        this.tag.deleteUserBookTag(this);
        this.userBook.deleteTag(this);
    }
}
