package jy.study.place.application;

import jy.study.place.dto.PlaceSearchKeywordDto;

import java.util.List;

public interface PlaceSearchKeywordService {

    /**
     * 검색횟수 상위 10개 키워드 조회
     * @return 검색 키워드 리스트
     */
    List<PlaceSearchKeywordDto> getCountTop10Keywords();
}
