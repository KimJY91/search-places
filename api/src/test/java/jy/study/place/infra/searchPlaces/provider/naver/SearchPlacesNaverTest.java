package jy.study.place.infra.searchPlaces.provider.naver;

import jy.study.place.domain.entity.Place;
import jy.study.place.infra.searchPlaces.provider.naver.SearchPlacesNaver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SearchPlacesNaverTest {

    @Autowired
    SearchPlacesNaver searchPlacesNaver;

    @Test
    @DisplayName("네이버 API로 장소 조회")
    void search() {
        //given
        String keyword = "곱창";
        int size = 5;

        //when
        List<Place> searchPlaces = searchPlacesNaver.search(keyword, size);

        //then
        assertNotNull(searchPlaces);
        assertEquals(size, searchPlaces.size());
    }

}