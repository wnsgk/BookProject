package com.book.service;

import com.book.domain.book.*;
import com.book.domain.user.User;
import com.book.exception.InvalidReqBodyException;
import com.book.exception.book.DuplicateBookException;
import com.book.repository.BookRepository;
import com.book.repository.MyBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.Document;
import javax.transaction.Transactional;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Value("${naver.id}")
    private String id;

    @Value("${naver.secret}")
    private String secret;

    @Value("${aladin.url}")
    private String DETAIL_URL;
    private final BookRepository bookRepository;
    private final MyBookRepository myBookRepository;

    private final String SEARCH_URL = "https://openapi.naver.com/v1/search/book.json";
    //private final String DETAIL_URL = "https://openapi.naver.com/v1/search/book_adv.xml";


    public SearchDto search(String keyword, int start){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity();
        URI targetUrl = UriComponentsBuilder
                .fromUriString(SEARCH_URL)
                .queryParam("query", keyword)
                .queryParam("start", start)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, SearchDto.class).getBody();
    }

    public SearchDetailDto searchIsbn(String isbn) {
        RestTemplate restTemplate = new RestTemplate();

        URI targetUrl = UriComponentsBuilder
                .fromUriString(DETAIL_URL)
                .queryParam("ItemId", isbn)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return restTemplate.exchange(targetUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), SearchDetailDto.class).getBody();
    }

    public boolean validIsbn(List<SearchDetailDto.Items> items, String isbn){
        if(!items.isEmpty() && (isbn.length() == 13 || isbn.length() == 10)){
            return true;
        }
        return false;
    }

    public BookResDto searchDetail(String isbn) {
        List<SearchDetailDto.Items> items = searchIsbn(isbn).getItem();
        if (!validIsbn(items, isbn))
            throw new InvalidParameterException("isbn = " + isbn);

        SearchDetailDto.Items item = items.get(0);
        return BookResDto.builder()
                .title(item.title)
                .link(item.link)
                .image(item.cover)
                .author(item.author)
                .publisher(item.publisher)
                .isbn(item.isbn13)
                .description(item.description)
                .page(item.subInfo.itemPage)
                .build();
    }

    private HttpEntity<String> getHttpEntity() { //헤더에 인증 정보 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", id);
        httpHeaders.set("X-Naver-Client-Secret", secret);
        return new HttpEntity<>(httpHeaders);
    }

}
