package jy.study.place.infra.searchPlaces.provider.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.infra.searchPlaces.provider.SearchPlacesProvider;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;

@Component
public class SearchPlacesKakao extends SearchPlacesProvider implements SearchPlaces {

    public SearchPlacesKakao(HttpClient httpClient, ObjectMapper objectMapper, KakaoConfig kakaoConfig) {
        super(httpClient, objectMapper, kakaoConfig.getUrl(), kakaoConfig.getHeaders());
    }

    @Override
    public List<Place> search(String keyword, int size) {
        Map<String, String> params = Map.of("query", keyword, "size", String.valueOf(size));
        return search(params, SearchPlacesKaKaoResult.class);
    }
}
