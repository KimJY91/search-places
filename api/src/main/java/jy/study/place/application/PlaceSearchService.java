package jy.study.place.application;

import jy.study.place.dto.PlaceDto;

import java.util.List;

public interface PlaceSearchService {

    /**
     * 키워드에 대한 장소 리스트 조회
     * @param keyword 키워드
     * @return 장소 리스트
     */
    List<PlaceDto> search(String keyword);
}
