package jy.study.place.infra.searchPlaces.provider;

import java.util.List;

public interface SearchPlacesProvider {

    List<? extends SearchPlace> search(String keyword, int size);
}
