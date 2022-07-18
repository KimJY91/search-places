package jy.study.place.domain.service;

import jy.study.place.domain.entity.Place;

import java.util.List;

public interface SearchPlaces {

    /**
     * 키워드에 대한 장소 리스트 검색
     * @param keyword 키워드
     * @param size 장소 검색 개수
     * @return 장소 리스트
     */
    List<Place> search(String keyword, int size);
}
