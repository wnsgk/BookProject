package com.book.service;

import com.book.domain.book.UserBook;
import com.book.domain.tag.Tag;
import com.book.domain.tag.UserBookTag;
import com.book.exception.book.TagNotFoundException;
import com.book.repository.TagRepository;
import com.book.repository.UserBookTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final UserBookTagRepository userBookTagRepository;

    @Transactional(readOnly = true)
    public List<UserBook> searchTag(String name){
        Tag tag = tagRepository.findByName(name).get();
        List<UserBookTag> userBookTags = userBookTagRepository.findAllUserBook(tag.getId());
        List<UserBook> userBookList = new ArrayList<>();
        for (UserBookTag userBookTag: userBookTags) {
            userBookList.add(userBookTag.getUserBook());
        }
        return userBookList;
    }

    @Transactional
    public void addTag(String name, UserBook userBook){
        Tag tag = getTag(name);
        UserBookTag userBookTag = UserBookTag.builder()
                .tag(tag)
                .userBook(userBook)
                .build();
        userBookTagRepository.save(userBookTag);
        tag.addTag(userBookTag);
        userBook.addTag(userBookTag);
    }

    public Tag getTag(String name){
        return tagRepository.findByName(name).orElse(createTag(name));
    }

    public Tag createTag(String name){
        Tag tag = Tag.builder()
                .name(name)
                .build();
        tagRepository.save(tag);
        return tag;
    }

    @Transactional
    public void updateTag(UserBook userBook, List<String> tags){
        for (UserBookTag userBookTag :userBook.getTags()) {
            if(!tags.contains(userBookTag.getTag().getName())){
                deleteTag(userBookTag.getId());
            }
        }
        for (String tagName: tags) {
            if(!userBook.getTags().contains(tagName)) {
                addTag(tagName, userBook);
            }
        }
    }

    @Transactional
    public void deleteTag(Long id){
        UserBookTag userBookTag = userBookTagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("태그가 존재하지 않습니다."));
        userBookTag.delete();
        tagRepository.deleteById(id);
    }

}
