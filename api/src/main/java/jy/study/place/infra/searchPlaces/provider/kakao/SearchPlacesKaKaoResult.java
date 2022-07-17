package jy.study.place.infra.searchPlaces.provider.kakao;

import jy.study.place.infra.searchPlaces.provider.SearchPlace;
import lombok.Getter;

import java.util.List;

/**
 * https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword 참고
 */
@Getter
public class SearchPlacesKaKaoResult {

    @Getter
    public static class Document extends SearchPlace {
        //장소 id
        private String id;
        //장소명, 업체명
        private String place_name;
        //중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재), 단위 meter
        private String distance;
        //장소 상세페이지 URL
        private String place_url;
        //카테고리 이름
        private String category_name;
        //전체 지번 주소
        private String address_name;
        //전체 도로명 주소
        private String road_address_name;
        //전화번호
        private String phone;
        //중요 카테고리만 그룹핑한 카테고리 그룹 코드
        private String category_group_code;
        //중요 카테고리만 그룹핑한 카테고리 그룹명
        private String category_group_name;
        //X 좌표값, 경위도인 경우 longitude (경도)
        private String x;
        //Y 좌표값, 경위도인 경우 latitude(위도)
        private String y;

        @Override
        public String getName() {
            return place_name;
        }

        @Override
        public String address() {
            return address_name;
        }

        @Override
        public String roadAddress() {
            return road_address_name;
        }
    }

    @Getter
    public static class Meta {
        //질의어의 지역 및 키워드 분석 정보
        private RegionInfo same_name;
        //검색어에 검색된 문서 수
        private long total_count;
        //total_count 중 노출 가능 문서 수 (최대: 45)
        private int pageable_count;
        //현재 페이지가 마지막 페이지인지 여부
        private boolean is_end;
    }

    @Getter
    public static class RegionInfo {
        //질의어에서 인식된 지역의 리스트
        private List<String> region;
        //질의어에서 지역 정보를 제외한 키워드
        private String keyword;
        //인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
        private String selected_region;
    }

    private List<Document> documents;

    private Meta meta;
}
