package jy.study.place.dto;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceSearchKeywordDto {

    private String keyword;

    private long count;

    public PlaceSearchKeywordDto(PlaceSearchKeyword placeSearchKeyword) {
        this.keyword = placeSearchKeyword.getKeyword();
        this.count = placeSearchKeyword.getCount();
    }
}
