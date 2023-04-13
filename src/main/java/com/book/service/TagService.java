package com.book.service;

import com.book.domain.MyBook.MyBook;
import com.book.domain.tag.Tag;
import com.book.domain.tag.MyBookTag;
import com.book.repository.TagRepository;
import com.book.repository.MyBookTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final MyBookTagRepository myBookTagRepository;

    @Transactional(readOnly = true)
    public List<MyBook> searchTag(String name){
        Tag tag = tagRepository.findByName(name).get();

        List<MyBookTag> myBookTags = myBookTagRepository.findAllMyBooks(tag);
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
        tag.addTag(myBookTag);
        myBook.addTag(myBookTag);
        myBookTagRepository.save(myBookTag);
    }

    public Tag getTag(String name){
        Optional<Tag> tag = tagRepository.findByName(name);
        if(tag.isEmpty()){
            return createTag(name);
        } else {
            return tag.get();
        }
    }

    public Tag createTag(String name){
        System.out.println("name = " + name);
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
                deleteTag(myBookTag);
            }
        }
        for (String tagName: tags) {
            if(!myBook.getTags().contains(tagName)) {
                addTag(tagName, myBook);
            }
        }
    }

    @Transactional
    public void deleteTag(MyBookTag myBookTag){
        myBookTag.delete();
        myBookTagRepository.deleteById(myBookTag.getId());
    }

    @Transactional
    public void deleteTag(MyBook myBook){
        for (MyBookTag myBookTag: myBook.getTags()) {
            deleteTag(myBookTag);
        }
    }

}
