package jy.study.place.infra.searchPlaces.provider.kakao;

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
public class SearchPlacesKakao implements SearchPlacesProvider {

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    @Override
    public List<? extends SearchPlace> search(String keyword, int size) {
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
            return result.getDocuments();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(0);
    }
}
