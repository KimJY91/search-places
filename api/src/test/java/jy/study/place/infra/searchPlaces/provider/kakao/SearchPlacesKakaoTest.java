package jy.study.place.infra.searchPlaces.provider.kakao;

import jy.study.place.domain.entity.Place;
import jy.study.place.infra.searchPlaces.provider.kakao.SearchPlacesKakao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchPlacesKakaoTest {

    @Autowired
    SearchPlacesKakao searchPlacesKakao;

    @Test
    @DisplayName("카카오 API로 장소 조회")
    void search() {
        //given
        String keyword = "곱창";
        int size = 5;

        //when
        List<Place> searchPlaces = searchPlacesKakao.search(keyword, size);

        //then
        assertNotNull(searchPlaces);
        assertEquals(size, searchPlaces.size());
    }

}