package jy.study.place.application.impl;

import jy.study.place.application.PlaceSearchKeywordService;
import jy.study.place.domain.entity.PlaceSearchKeyword;
import jy.study.place.dto.PlaceSearchKeywordDto;
import jy.study.place.infra.repository.PlaceSearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceSearchKeywordServiceImpl implements PlaceSearchKeywordService {

    private final PlaceSearchKeywordRepository placeSearchKeywordRepository;

    /**
     * 검색횟수 상위 10개 키워드 조회
     * @return 검색 키워드 리스트
     */
    @Override
    public List<PlaceSearchKeywordDto> getCountTop10Keywords() {
        List<PlaceSearchKeyword> list = placeSearchKeywordRepository.findTop10ByOrderByCountDesc();
        return list.stream().map(PlaceSearchKeywordDto::new).collect(Collectors.toList());
    }
}
