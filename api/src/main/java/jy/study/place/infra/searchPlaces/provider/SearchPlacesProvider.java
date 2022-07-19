package jy.study.place.infra.searchPlaces.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.exception.SearchPlaceFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

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

    protected <T> T search(Map<String, String> params, Class<T> bodyClass) throws IOException, InterruptedException {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(params);

        URI uri = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParams(multiValueMap)
                .build().toUri();

        HttpRequest httpRequest = httpRequestBuilder.uri(uri).build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            log.error("response : {}", response);
            throw new SearchPlaceFailException();
        }

        return objectMapper.readValue(response.body(), bodyClass);
    }
}
