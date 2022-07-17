package jy.study.place.application;

import jy.study.place.dto.PlaceDto;

import java.util.List;

public interface SearchPlacesService {

    List<PlaceDto> search(String keyword);

}
