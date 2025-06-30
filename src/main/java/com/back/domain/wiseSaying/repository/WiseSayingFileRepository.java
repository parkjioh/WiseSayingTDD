package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;
import com.back.standard.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository {

    public String getTableDirPath() {
        return "db/wiseSaying";
    }

    public String getEntityFilePath(WiseSaying wiseSaying){
        return getTableDirPath() + "/%d.json".formatted(wiseSaying.getId());
    }

    public String getEntityFilePath(int id){
        return getTableDirPath() + "/%d.json".formatted(id);
    }

    public String getLastIdFilePath() {
        return getTableDirPath() + "/lastId.txt";
    }

    private void setLastId(int newId) {
        Util.file.set(getLastIdFilePath(), newId);
    }


    private int getLastId() {
        return Util.file.getAsInt(getLastIdFilePath(),0);
    }

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()){
            int newId = getLastId() + 1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }
        Map<String,Object> wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJsonStr = Util.file.json.toString(wiseSayingMap);
        Util.file.set(getEntityFilePath(wiseSaying), wiseSayingJsonStr);
    }

    public Optional<WiseSaying> findById(int id) {
        String wiseSayingJsonStr = Util.file.get(getEntityFilePath(id),"");

        if(wiseSayingJsonStr.isBlank()) return Optional.empty();

        Map<String,Object> wiseSayingMap = Util.file.json.toMap(wiseSayingJsonStr);
        return Optional.of(new WiseSaying(wiseSayingMap));
    }

    public boolean delete(WiseSaying wiseSaying) {
        return Util.file.delete(getEntityFilePath(wiseSaying));
    }

    public void clear() {
        Util.file.rmdir(getTableDirPath());
    }

    public Page<WiseSaying> findForList(Pageable pageable) {
        List<WiseSaying> content = findAll();
        int totalCount = content.size();

        return new Page<>(
                totalCount,
                pageable.getPageNo(),
                pageable.getPageSize(),
                 content
        );
    }

    private List<WiseSaying> findAll() {
        return Util.file.walkRegularFiles(
                getTableDirPath(),
                "\\d+\\.json"
        ).map(path -> Util.file.get(path.toString(), ""))
                .map(Util.file.json::toMap)
                .map(WiseSaying::new)
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .toList();
    }
}
