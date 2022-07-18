package jy.study.place.infra.searchPlaces.combination;

import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("searchPlaces")
@RequiredArgsConstructor
public class SearchPlacesKakaoAndNaver implements SearchPlaces {

    private final SearchPlaces searchPlacesKakao;

    private final SearchPlaces searchPlacesNaver;

    @Cacheable("searchPlaces")
    @Override
    public List<Place> search(String keyword, int size) {
        List<Place> placesFromKakao = searchPlacesKakao.search(keyword, size);
        List<Place> placesFromNaver = searchPlacesNaver.search(keyword, size);
        return combine(placesFromKakao, placesFromNaver, size);
    }

    private List<Place> combine(List<Place> kakaoPlaces, List<Place> naverPlaces, int size) {
        List<Place> combinedList = new ArrayList<>(10);
        HashMap<Place, Boolean> addedPlacesCheckMap = new HashMap<>();
        int addedEqualityCnt = 0;

        if (CollectionUtils.isEmpty(kakaoPlaces))
            return naverPlaces;

        //카카오와 네이버에서 검색된 동일한 장소 추가
        for (Place kakaoPlace : kakaoPlaces) {
            for (Place naverPlace : naverPlaces) {
                if (kakaoPlace.checkSamePlace(naverPlace)) {
                    addedEqualityCnt++;
                    combinedList.add(kakaoPlace);
                    addedPlacesCheckMap.put(kakaoPlace, true);
                    addedPlacesCheckMap.put(naverPlace, true);
                    break;
                }
            }
        }

        int realNaverListSize = (naverPlaces.size() - addedEqualityCnt);
        int requiredKakaoSize = (size - addedEqualityCnt) / 2;
        if (realNaverListSize < size - addedEqualityCnt - requiredKakaoSize ) {
            requiredKakaoSize += size - requiredKakaoSize - realNaverListSize;
        }
        int addedKakaoCnt = 0;

        //카카오에서 검색된 장소 추가
        for (int i = 0; addedKakaoCnt < requiredKakaoSize && i < kakaoPlaces.size(); i++) {
            if (!addedPlacesCheckMap.getOrDefault(kakaoPlaces.get(i), false)) {
                combinedList.add(kakaoPlaces.get(i));
                addedPlacesCheckMap.put(kakaoPlaces.get(i), true);
                addedKakaoCnt++;
            }
        }

        //네이버에서 검색된 장소 추가
        for (int i = 0; combinedList.size() < size && i < naverPlaces.size(); i++) {
            if (!addedPlacesCheckMap.getOrDefault(naverPlaces.get(i), false)) {
                combinedList.add(naverPlaces.get(i));
                addedPlacesCheckMap.put(naverPlaces.get(i), true);
            }
        }

        return combinedList;
    }
}
