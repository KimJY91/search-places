package jy.study.place.domain.service;

public interface KeywordCountPlus {

    /**
     * 검색 키워드 카운드 증가
     * @param keyword 검색 키워드
     */
    void plusCount(String keyword);
}
