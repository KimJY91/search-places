package jy.study.place.infra.searchPlaces.provider.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.exception.SearchPlaceFailException;
import jy.study.place.infra.searchPlaces.provider.SearchPlacesProvider;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SearchPlacesNaver extends SearchPlacesProvider implements SearchPlaces {

    public SearchPlacesNaver(HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient, objectMapper, "https://openapi.naver.com/v1/search/local.json",
                Map.of("X-Naver-Client-Id", "wnsJKaKe4a7uzIXacGUy", "X-Naver-Client-Secret", "KOECnMtwKe"));
    }

    @Override
    public List<Place> search(String keyword, int size) {
        Map<String, String> params = Map.of("query", keyword, "display", String.valueOf(size));

        try {
            SearchPlacesNaverResult result = search(params, SearchPlacesNaverResult.class);

            if(result.getTotal() == 0) {
                return List.of();
            } else {
                return result.getItems().stream()
                        .map(i -> new Place(
                                i.getTitle().replaceAll("<[^>]*>", " "),
                                i.getAddress(),
                                i.getRoadAddress()))
                        .collect(Collectors.toList());
            }

        } catch (SearchPlaceFailException e) {
            throw e;
        } catch (Exception e) {
            throw new SearchPlaceFailException(e);
        }
    }
}
