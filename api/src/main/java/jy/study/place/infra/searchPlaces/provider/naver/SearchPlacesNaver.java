package jy.study.place.infra.searchPlaces.provider.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.infra.searchPlaces.provider.SearchPlacesProvider;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;

@Component
public class SearchPlacesNaver extends SearchPlacesProvider implements SearchPlaces {

    public SearchPlacesNaver(HttpClient httpClient, ObjectMapper objectMapper, NaverConfig naverConfig) {
        super(httpClient, objectMapper, naverConfig.getUrl(), naverConfig.getHeaders());
    }

    @Override
    public List<Place> search(String keyword, int size) {
        Map<String, String> params = Map.of("query", keyword, "display", String.valueOf(size));
        return search(params, SearchPlacesNaverResult.class);
    }
}
