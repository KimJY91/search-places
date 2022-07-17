package jy.study.place.api;

import jy.study.common.dto.CommonResponse;
import jy.study.place.application.PlaceSearchKeywordService;
import jy.study.place.application.PlaceSearchService;
import jy.study.place.dto.PlaceDto;
import jy.study.place.dto.PlaceSearchKeywordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/places")
@RestController
@RequiredArgsConstructor
public class PlaceApi {

    private final PlaceSearchService searchPlacesService;

    private final PlaceSearchKeywordService placeSearchKeywordService;

    /**
     * 키워드에 대한 장소 리스트 검색
     * @param keyword 키워드
     * @return 장소 리스트
     */
    @GetMapping
    public CommonResponse<List<PlaceDto>> searchPlaces(@RequestParam String keyword) {
        return new CommonResponse<>(searchPlacesService.search(keyword));
    }

    /**
     * 검색횟수 상위 10개 키워드 조회
     * @return 키워드 리스트
     */
    @GetMapping("/keywords/count-top-10")
    public CommonResponse<List<PlaceSearchKeywordDto>> getCountTop10Keywords() {
        return new CommonResponse<>(placeSearchKeywordService.getCountTop10Keywords());
    }
}
