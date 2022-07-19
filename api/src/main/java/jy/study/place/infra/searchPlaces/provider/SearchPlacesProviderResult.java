package jy.study.place.infra.searchPlaces.provider;

import jy.study.place.infra.searchPlaces.provider.kakao.SearchPlacesKaKaoResult;

import java.util.List;

public interface SearchPlacesProviderResult {

    public static interface Item {

        //장소 이름
        String getName();

        //주소
        String getAddress();

        //도로명 주소
        String getRoadAddress();
    }

    //총 조회 개수
    long getTotalCount();

    //조회한 장소 리스트
    List<? extends  Item> getItems();
}
