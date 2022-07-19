package jy.study.place.infra.searchPlaces.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.exception.SearchPlaceFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchPlacesKakao implements SearchPlaces {

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    @Override
    public List<Place> search(String keyword, int size) {
        URI uri = UriComponentsBuilder
                .fromUri(URI.create("http://dapi.kakao.com/v2/local/search/keyword.JSON"))
                .queryParam("query", keyword)
                .queryParam("size", size)
                .build().toUri();

        HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                .header("Authorization", "KakaoAK 6c9121f4dc84325e197cb093f3d975e3")
                .uri(uri)
                .build();

        try {


            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            SearchPlacesKaKaoResult result = objectMapper.readValue(response.body(), SearchPlacesKaKaoResult.class);

            if (result.getMeta().getTotal_count() == 0) {
                return List.of();
            } else {
                return result.getDocuments().stream()
                        .map(d -> new Place(d.getPlace_name(), d.getAddress_name(), d.getRoad_address_name()))
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            throw new SearchPlaceFailException(e);
        }
    }
}
