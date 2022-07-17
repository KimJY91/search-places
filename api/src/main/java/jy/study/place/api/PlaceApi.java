package jy.study.place.api;

import jy.study.common.dto.CommonResponse;
import jy.study.place.application.SearchPlacesService;
import jy.study.place.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceApi {

    private final SearchPlacesService searchPlacesService;

    @GetMapping("/places")
    public CommonResponse<List<PlaceDto>> searchPlaces(@RequestParam String keyword) {
        return new CommonResponse<>(searchPlacesService.search(keyword));
    }
}
