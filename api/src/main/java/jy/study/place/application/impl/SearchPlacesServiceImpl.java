package jy.study.place.application.impl;

import jy.study.place.application.SearchPlacesService;
import jy.study.place.domain.service.SearchPlaces;
import jy.study.place.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchPlacesServiceImpl implements SearchPlacesService {

    private final SearchPlaces searchPlaces;

    @Override
    public List<PlaceDto> search(String keyword) {
        return searchPlaces.search(keyword);
    }
}
