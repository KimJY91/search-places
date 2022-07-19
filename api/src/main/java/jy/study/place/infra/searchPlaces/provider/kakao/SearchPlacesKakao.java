package jy.study.place.infra.searchPlaces.provider.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.exception.SearchPlaceFailException;
import jy.study.place.infra.searchPlaces.provider.SearchPlacesProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SearchPlacesKakao extends SearchPlacesProvider implements SearchPlaces {

    public SearchPlacesKakao(HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient, objectMapper, "http://dapi.kakao.com/v2/local/search/keyword.JSON",
                Map.of("Authorization", "KakaoAK 6c9121f4dc84325e197cb093f3d975e3"));
    }

    @Override
    public List<Place> search(String keyword, int size) {
        Map<String, String> params = Map.of("query", keyword, "size", String.valueOf(size));

        try {
            SearchPlacesKaKaoResult result = search(params, SearchPlacesKaKaoResult.class);

            if (result.getMeta().getTotal_count() == 0) {
                return List.of();
            } else {
                return result.getDocuments().stream()
                        .map(d -> new Place(d.getPlace_name(), d.getAddress_name(), d.getRoad_address_name()))
                        .collect(Collectors.toList());
            }

        } catch (SearchPlaceFailException e) {
          throw e;
        } catch (Exception e) {
            throw new SearchPlaceFailException(e);
        }
    }
}
