package jy.study.place.infra.searchPlaces;

import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchPlacesFromExternalApi implements SearchPlaces {

    @Override
    public List<PlaceDto> search(String keyword) {
        return List.of(
                new PlaceDto("장소1", "주소1", "도로명주소1"),
                new PlaceDto("장소2", "주소2", "도로명주소2"),
                new PlaceDto("장소3", "주소3", "도로명주소3")
        );
    }
}
