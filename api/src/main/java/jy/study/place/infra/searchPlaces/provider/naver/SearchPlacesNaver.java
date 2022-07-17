package jy.study.place.infra.searchPlaces.provider.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.infra.searchPlaces.provider.SearchPlace;
import jy.study.place.infra.searchPlaces.provider.SearchPlacesProvider;
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

@Component
@RequiredArgsConstructor
public class SearchPlacesNaver implements SearchPlacesProvider {

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    @Override
    public List<? extends SearchPlace> search(String keyword, int size) {
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
            return result.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(0);
    }
}
