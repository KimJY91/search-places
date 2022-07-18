package jy.study.place.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceTest {

    @Test
    @DisplayName("장소 비교 - 주소가 같음")
    void sameNameAndAddress() {
        //given
        Place place1 = new Place("장소", "주소", "도로명주소");
        Place place2 = new Place("장소", "주소", "도로명주소");

        //when & then
        assertTrue(place1.checkSamePlace(place2));
    }

    @Test
    @DisplayName("장소 비교 - 이름, 주소 다름")
    void diffNameAndAddress() {
        //given
        Place place1 = new Place("장소1", "주소1", "도로명주소1");
        Place place2 = new Place("장소2", "주소2", "도로명주소2");

        //when & then
        assertFalse(place1.checkSamePlace(place2));
    }

    @Test
    @DisplayName("장소 비교 - 이름이 같고 주소가 유사함")
    void diffNameAndSimilarAddress() {
        //given
        Place place1 = new Place("장소1", "울산시 동구 전하동 133-1 OO빌딩 ", "도로명주소1");
        Place place2 = new Place("장소1", "울산광역시 동구 전하동 133-1", "도로명주소2");

        //when & then
        assertTrue(place1.checkSamePlace(place2));
    }
}
