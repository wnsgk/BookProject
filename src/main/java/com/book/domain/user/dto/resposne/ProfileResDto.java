package com.book.domain.user.dto.resposne;

import com.book.domain.MyBook.MyBook;
import com.book.domain.MyBook.dto.resposne.MyBookResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfileResDto {

    private String name;

    private String intro;

    private List<MyBookResDto> myBooks;

    public void setMyBooks(List<MyBook> books){
        for (MyBook book : books) {
            myBooks.add(book.toResDto());
        }
    }
}
