package com.book.service;

import com.book.domain.book.Book;
import com.book.domain.recommend.Recommend;
import com.book.domain.recommend.dto.request.RecommendCreate;
import com.book.domain.recommend.dto.request.RecommendUpdate;
import com.book.domain.recommend.dto.response.RecommendResDto;
import com.book.domain.user.User;
import com.book.exception.book.BookNotFoundException;
import com.book.repository.BookRepository;
import com.book.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecommendRepository recommendRepository;
    private final BookRepository bookRepository;

    public void createRecommend(User user, RecommendCreate recommend){
        Book book = bookRepository.findById(recommend.getBookId()).orElseThrow(() -> new BookNotFoundException("존재하지 않는 책입니다."));
        Recommend rec = Recommend.builder()
                .book(book)
                .user(user)
                .content(recommend.getContent())
                .build();
        recommendRepository.save(rec);
    }

    public void deleteRecommend(Long id) {
        Recommend recommend = getRecommend(id);
        User user = recommend.getUser();
        Book book = recommend.getBook();
        user.deleteRec(recommend);
        book.deleteRec(recommend);
        recommendRepository.deleteById(id);
    }

    public void updateRecommend(RecommendUpdate update) {
        Recommend recommend = getRecommend(update.getId());
        recommend.updateContent(update.getContent());
    }

    public Recommend getRecommend(Long id) {
        return recommendRepository.findById(id).orElseThrow(() -> new BookNotFoundException("존재하지 않는 책입니다."));
    }

    public RecommendResDto getRecommendRes(Recommend recommend){
        Book book = recommend.getBook();
        User user = recommend.getUser();
        return RecommendResDto.builder()
                .title(book.getTitle())
                .bookImage(book.getImage())
                .userId(user.getId())
                .nickname(user.getName())
                .userImage(user.getPhoto())
                .content(recommend.getContent()).build();
    }
}
