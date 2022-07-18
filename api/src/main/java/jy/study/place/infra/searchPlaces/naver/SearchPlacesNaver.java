package jy.study.place.infra.searchPlaces.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.exception.SearchPlaceFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchPlacesNaver implements SearchPlaces {

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    @Override
    public List<Place> search(String keyword, int size) {
        URI uri = UriComponentsBuilder
                .fromUri(URI.create("https://openapi.naver.com/v1/search/local.json"))
                .queryParam("query", keyword)
                .queryParam("display", size)
                .build().toUri();

        HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                .header("X-Naver-Client-Id", "wnsJKaKe4a7uzIXacGUy")
                .header("X-Naver-Client-Secret", "KOECnMtwKe")
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            SearchPlacesNaverResult result = objectMapper.readValue(response.body(), SearchPlacesNaverResult.class);

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

        } catch (Exception e) {
            throw new SearchPlaceFailException(e);
        }
    }
}
