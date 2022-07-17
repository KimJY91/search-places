package jy.study.place.domain.service;

import jy.study.place.dto.PlaceDto;

import java.util.List;

public interface SearchPlaces {

    List<PlaceDto> search(String keyword);
}
