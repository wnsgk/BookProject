package com.book.service;

import com.book.domain.MyBook.MyBook;
import com.book.domain.tag.Tag;
import com.book.domain.tag.MyBookTag;
import com.book.exception.book.TagNotFoundException;
import com.book.repository.TagRepository;
import com.book.repository.MyBookTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final MyBookTagRepository myBookTagRepository;

    @Transactional(readOnly = true)
    public List<MyBook> searchTag(String name){
        Tag tag = tagRepository.findByName(name).get();
        List<MyBookTag> myBookTags = myBookTagRepository.findMyBooks(tag.getId());
        List<MyBook> myBookList = new ArrayList<>();
        for (MyBookTag myBookTag : myBookTags) {
            myBookList.add(myBookTag.getMyBook());
        }
        return myBookList;
    }

    @Transactional
    public void addTag(String name, MyBook myBook){
        Tag tag = getTag(name);
        MyBookTag myBookTag = MyBookTag.builder()
                .tag(tag)
                .myBook(myBook)
                .build();
        myBookTagRepository.save(myBookTag);
        tag.addTag(myBookTag);
        myBook.addTag(myBookTag);
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
    public void updateTag(MyBook myBook, List<String> tags){
        for (MyBookTag myBookTag : myBook.getTags()) {
            if(!tags.contains(myBookTag.getTag().getName())){
                deleteTag(myBookTag.getId());
            }
        }
        for (String tagName: tags) {
            if(!myBook.getTags().contains(tagName)) {
                addTag(tagName, myBook);
            }
        }
    }

    @Transactional
    public void deleteTag(Long id){
        MyBookTag myBookTag = myBookTagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("태그가 존재하지 않습니다."));
        myBookTag.delete();
        tagRepository.deleteById(id);
    }

}
