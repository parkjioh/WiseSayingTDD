package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.global.Appconfig;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;
import com.back.standard.util.Util;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);

    Optional<WiseSaying> findById(int id);

    boolean delete(WiseSaying wiseSaying);

    Page<WiseSaying> findforList(Pageable pageable);

    Page<WiseSaying> findforListByContentContaining(String keyword, Pageable pageable);

    Page<WiseSaying> findforListByAuthorContaining(String keyword, Pageable pageable);

    Page<WiseSaying> findforListByContentContainingOrAuthorContaining(String keyword1, String keyword2, Pageable pageable);

    List<WiseSaying> findAll();

    default String archive(){
        List<WiseSaying> all = findAll();

        String json = Util.json.toString(
                all
                        .stream()
                        .map(WiseSaying::toMap)
                        .toList()
        );

        String filePath = getArchiveFilePath();

        Util.file.set(filePath, json);

        return filePath;
    }

    default String getArchiveFilePath(){
        return Appconfig.getMode() + "Db/wiseSaying/data.json";
    }
}