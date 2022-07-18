package jy.study.place.domain.service.impl;

import jy.study.place.domain.service.KeywordCountPlus;
import jy.study.place.infra.repository.PlaceSearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordCountPlusImpl implements KeywordCountPlus {

    private final PlaceSearchKeywordRepository placeSearchKeywordRepository;

    /**
     * 검색 키워드 카운드 증가
     * @param keyword 검색 키워드
     */
    @Override
    public void plusCount(String keyword) {
        int updatedRowCount = placeSearchKeywordRepository.plusCount(keyword);

        if (updatedRowCount == 0) {
            try {
                placeSearchKeywordRepository.init(keyword);
            } catch (DataIntegrityViolationException ignored) {}

            placeSearchKeywordRepository.plusCount(keyword);
        }
    }
}
