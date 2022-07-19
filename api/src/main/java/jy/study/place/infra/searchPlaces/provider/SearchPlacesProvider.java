package jy.study.place.infra.searchPlaces.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.exception.SearchPlaceFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class SearchPlacesProvider {

    private static final Logger log = LoggerFactory.getLogger(SearchPlacesProvider.class);

    private final String url;

    private final HttpRequest.Builder httpRequestBuilder;

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    public SearchPlacesProvider(
            HttpClient httpClient, ObjectMapper objectMapper, String url, Map<String, String> headers
    ) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.url = url;
        this.httpRequestBuilder = HttpRequest.newBuilder().GET();

        for (String key : headers.keySet())
            httpRequestBuilder.header(key, headers.get(key));
    }

    protected List<Place> search(Map<String, String> params, Class<? extends SearchPlacesProviderResult> bodyClass) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(params);

        URI uri = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParams(multiValueMap)
                .build().toUri();

        HttpResponse<String> response;

        try {
            HttpRequest httpRequest = httpRequestBuilder.uri(uri).build();
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new SearchPlaceFailException(e);
        }

        responseCheck(response);

        try {
            SearchPlacesProviderResult result = objectMapper.readValue(response.body(), bodyClass);
            return convertResult(result);
        } catch (JsonProcessingException e) {
            throw new SearchPlaceFailException(e);
        }

    }

    /**
     * response 체크
     * @param response HttpResponse
     */
    protected void responseCheck(HttpResponse<String> response) {
        if (response == null) {
            log.error("response is null!!!");
            throw new SearchPlaceFailException();

        } else if (response.statusCode() != 200) {
            log.error("response : {}", response);
            throw new SearchPlaceFailException();
        }
    }

    /**
     * body 객체의 장소 데이터를 Place 도메인 객체로 변환
     * @param result response body
     * @return {@link Place} 리스트
     */
    protected List<Place> convertResult(SearchPlacesProviderResult result) {
        if (result.getTotalCount() == 0) {
            return List.of();
        } else {
            return result.getItems().stream()
                    .map(i -> new Place(i.getName(), i.getAddress(), i.getRoadAddress()))
                    .collect(Collectors.toList());
        }
    }

}
