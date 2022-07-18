package jy.study.place.application.impl;

import jy.study.place.application.PlaceSearchService;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.KeywordCountPlus;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceSearchServiceImpl implements PlaceSearchService {

    private final SearchPlaces searchPlaces;

    private final KeywordCountPlus keywordCountPlus;

    /**
     * 키워드에 대한 장소 리스트 조회
     * @param keyword 키워드
     * @return 장소 리스트
     */
    @Override
    public List<PlaceDto> search(String keyword) {
        keywordCountPlus.plusCount(keyword);
        List<Place> places = searchPlaces.search(keyword, 10);
        return places.stream().map(PlaceDto::new).collect(Collectors.toList());
    }
}
