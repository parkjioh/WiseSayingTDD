package com.back.domain.wiseSaying.service;

import com.back.domain.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository ;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying write(String content, String author) {
       WiseSaying wiseSaying = new WiseSaying(content, author);

       wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findforList(String keywordType, String keyword) {
        if (keyword.isBlank()) return wiseSayingRepository.findforList();
        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findforListByCotentContaining(keyword);
            case "author" -> wiseSayingRepository.findforListByAuthorContaining(keyword);
            default -> throw new IllegalStateException("Unexpected value: " + keywordType);
        }   ;
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);

        if( wiseSaying == null )  return false;

        wiseSayingRepository.delete(wiseSaying);
        return true;
    }


    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }
}
