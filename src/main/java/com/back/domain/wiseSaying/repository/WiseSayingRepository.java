package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.Optional;

public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);

    Optional<WiseSaying> findById(int id);

    boolean delete(WiseSaying wiseSaying);

    Page<WiseSaying> findforList(Pageable pageable);

    Page<WiseSaying> findforListByContentContaining(String keyword, Pageable pageable);

    Page<WiseSaying> findforListByAuthorContaining(String keyword, Pageable pageable);

    Page<WiseSaying> findforListByContentContainingOrAuthorContaining(String keyword1, String keyword2, Pageable pageable);

    default String archive(){ return null; };
}