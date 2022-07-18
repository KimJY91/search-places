package jy.study.place.infra.searchPlaces.combination.kakaoAndNaver;

import jy.study.place.domain.entity.Place;
import jy.study.place.domain.service.SearchPlaces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchPlacesKakaoAndNaverTest {

    @Mock
    SearchPlaces searchPlacesKakao;

    @Mock
    SearchPlaces searchPlacesNaver;

    SearchPlaces searchPlaces;

    @BeforeEach
    void setUp() {
        searchPlaces = new SearchPlacesKakaoAndNaver(searchPlacesKakao, searchPlacesNaver);
    }

    @Test
    @DisplayName("장소 조회 - 장소 모두 다름")
    void searchCase1() {
        //given
        given(searchPlacesKakao.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소1", "주소1", "도로명주소1"),
                    new Place("장소2", "주소2", "도로명주소2"),
                    new Place("장소3", "주소3", "도로명주소3"),
                    new Place("장소4", "주소4", "도로명주소4"),
                    new Place("장소5", "주소5", "도로명주소5"),
                    new Place("장소6", "주소6", "도로명주소6"),
                    new Place("장소7", "주소7", "도로명주소7"),
                    new Place("장소8", "주소8", "도로명주소8"),
                    new Place("장소9", "주소9", "도로명주소9"),
                    new Place("장소10", "주소10", "도로명주소10")));
        given(searchPlacesNaver.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소11", "주소11", "도로명주소11"),
                        new Place("장소12", "주소12", "도로명주소12"),
                        new Place("장소13", "주소13", "도로명주소13"),
                        new Place("장소14", "주소14", "도로명주소14"),
                        new Place("장소15", "주소15", "도로명주소15")));

        //when
        List<Place> places = searchPlaces.search("테스트", 10);

        //then
        assertEquals(10, places.size());
        assertThat(places).extracting("name").containsExactly(
                "장소1", "장소2", "장소3", "장소4", "장소5", "장소11", "장소12", "장소13", "장소14", "장소15");
    }

    @Test
    @DisplayName("장소 조회 - 장소 모두 동일")
    void searchCase2() {
        //given
        given(searchPlacesKakao.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소1", "주소1", "도로명주소1"),
                        new Place("장소2", "주소2", "도로명주소2"),
                        new Place("장소3", "주소3", "도로명주소3"),
                        new Place("장소4", "주소4", "도로명주소4"),
                        new Place("장소5", "주소5", "도로명주소5"),
                        new Place("장소6", "주소6", "도로명주소6"),
                        new Place("장소7", "주소7", "도로명주소7"),
                        new Place("장소8", "주소8", "도로명주소8"),
                        new Place("장소9", "주소9", "도로명주소9"),
                        new Place("장소10", "주소10", "도로명주소10")));
        given(searchPlacesNaver.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소1", "주소1", "도로명주소1"),
                        new Place("장소2", "주소2", "도로명주소2"),
                        new Place("장소3", "주소3", "도로명주소3"),
                        new Place("장소4", "주소4", "도로명주소4"),
                        new Place("장소5", "주소5", "도로명주소5"),
                        new Place("장소6", "주소6", "도로명주소6"),
                        new Place("장소7", "주소7", "도로명주소7"),
                        new Place("장소8", "주소8", "도로명주소8"),
                        new Place("장소9", "주소9", "도로명주소9"),
                        new Place("장소10", "주소10", "도로명주소10")));

        //when
        List<Place> places = searchPlaces.search("테스트", 10);

        //then
        assertEquals(10, places.size());
        assertThat(places).extracting("name").containsExactly(
                "장소1", "장소2", "장소3", "장소4", "장소5", "장소6", "장소7", "장소8", "장소9", "장소10");
    }

    @Test
    @DisplayName("장소 조회 - 장소 일부 동일")
    void searchCase3() {
        //given
        given(searchPlacesKakao.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소1", "주소1", "도로명주소1"),
                        new Place("장소2", "주소2", "도로명주소2"),
                        new Place("장소3", "주소3", "도로명주소3"),
                        new Place("장소4", "주소4", "도로명주소4"),
                        new Place("장소5", "주소5", "도로명주소5"),
                        new Place("장소6", "주소6", "도로명주소6"),
                        new Place("장소7", "주소7", "도로명주소7"),
                        new Place("장소8", "주소8", "도로명주소8"),
                        new Place("장소20", "주소20", "도로명주소20"),
                        new Place("장소21", "주소21", "도로명주소21")));
        given(searchPlacesNaver.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소11", "주소11", "도로명주소11"),
                        new Place("장소8", "주소8", "도로명주소8"),
                        new Place("장소5", "주소5", "도로명주소5"),
                        new Place("장소12", "주소12", "도로명주소12"),
                        new Place("장소15", "주소15", "도로명주소15"),
                        new Place("장소13", "주소13", "도로명주소13")));

        //when
        List<Place> places = searchPlaces.search("테스트", 10);

        //then
        assertEquals(10, places.size());
        assertThat(places).extracting("name").containsExactly(
                "장소5", "장소8", "장소1", "장소2", "장소3", "장소4", "장소11", "장소12", "장소15", "장소13");
    }

    @Test
    @DisplayName("장소 조회 - 카카오 검색결과 없음")
    void searchCase4() {
        //given
        given(searchPlacesKakao.search(anyString(), anyInt())).willReturn(List.of());
        given(searchPlacesNaver.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소11", "주소11", "도로명주소11"),
                        new Place("장소8", "주소8", "도로명주소8"),
                        new Place("장소5", "주소5", "도로명주소5"),
                        new Place("장소12", "주소12", "도로명주소12"),
                        new Place("장소15", "주소15", "도로명주소15"),
                        new Place("장소13", "주소13", "도로명주소13")));

        //when
        List<Place> places = searchPlaces.search("테스트", 10);

        //then
        assertEquals(6, places.size());
        assertThat(places).extracting("name").containsExactly(
                "장소11", "장소8", "장소5", "장소12", "장소15", "장소13");
    }

    @Test
    @DisplayName("장소 조회 - 네이버 검색결과 없음")
    void searchCase5() {
        //given
        given(searchPlacesKakao.search(anyString(), anyInt())).willReturn(List.of());
        given(searchPlacesNaver.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소1", "주소1", "도로명주소1"),
                        new Place("장소2", "주소2", "도로명주소2"),
                        new Place("장소3", "주소3", "도로명주소3"),
                        new Place("장소4", "주소4", "도로명주소4"),
                        new Place("장소5", "주소5", "도로명주소5"),
                        new Place("장소6", "주소6", "도로명주소6"),
                        new Place("장소7", "주소7", "도로명주소7"),
                        new Place("장소8", "주소8", "도로명주소8")));

        //when
        List<Place> places = searchPlaces.search("테스트", 10);

        //then
        assertEquals(8, places.size());
        assertThat(places).extracting("name").containsExactly(
                "장소1", "장소2", "장소3", "장소4", "장소5", "장소6", "장소7", "장소8");
    }

    @Test
    @DisplayName("장소 조회 - 장소 일부 동일, 장소 개수 불충분")
    void searchCase6() {
        //given
        given(searchPlacesKakao.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소1", "주소1", "도로명주소1"),
                        new Place("장소2", "주소2", "도로명주소2"),
                        new Place("장소3", "주소3", "도로명주소3"),
                        new Place("장소4", "주소4", "도로명주소4")));
        given(searchPlacesNaver.search(anyString(), anyInt())).willReturn(
                List.of(new Place("장소3", "주소3", "도로명주소3"),
                        new Place("장소4", "주소4", "도로명주소3"),
                        new Place("장소7", "주소7", "도로명주소7")));

        //when
        List<Place> places = searchPlaces.search("테스트", 10);

        //then
        assertEquals(5, places.size());
        assertThat(places).extracting("name").containsExactly(
                "장소3", "장소4", "장소1", "장소2", "장소7");
    }
}