package jy.study.place.infra.searchPlaces.provider.naver;

import jy.study.place.infra.searchPlaces.provider.SearchPlacesProviderResult;
import lombok.Getter;

import java.util.List;

/**
 * https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD 참고
 */
@Getter
public class SearchPlacesNaverResult implements SearchPlacesProviderResult {

    @Getter
    public static class Document implements SearchPlacesProviderResult.Item {
        //검색 결과 업체, 기관명
        private String title;
        //검색 결과 업체, 기관의 상세 정보가 제공되는 네이버 페이지의 하이퍼텍스트 link
        private String link;
        //검색 결과 업체, 기관의 분류 정보를 제공
        private String category;
        //검색 결과 업체, 기관명에 대한 설명을 제공
        private String description;
        //빈 문자열 반환. 과거에 제공되던 항목이라 하위 호환성을 위해 존재
        private String telephone;
        //검색 결과 업체, 기관명의 주소를 제공
        private String address;
        //검색 결과 업체, 기관명의 도로명 주소를 제공
        private String roadAddress;
        //검색 결과 업체, 기관명 위치 정보의 x좌표
        private String mapx;
        //검색 결과 업체, 기관명 위치 정보의 y좌표
        private String mapy;

        @Override
        public String getName() {
            return title.replaceAll("<[^>]*>", " ");
        }
    }

    //검색 결과 문서의 총 개수
    private int total;

    //검색 결과 문서 중, 문서의 시작점
    private int start;

    //검색된 검색 결과의 개수
    private int display;

    private List<Document> items;

    @Override
    public long getTotalCount() {
        return total;
    }

}
