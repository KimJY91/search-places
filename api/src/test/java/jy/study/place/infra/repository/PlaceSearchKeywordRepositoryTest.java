package jy.study.place.infra.repository;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PlaceSearchKeywordRepositoryTest {

    @Autowired
    PlaceSearchKeywordRepository placeSearchKeywordRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("카운트 증가")
    void plusCount() {
        //given
        PlaceSearchKeyword keyword = new PlaceSearchKeyword("테스트", 1);
        entityManager.persist(keyword);
        entityManager.flush();
        entityManager.clear();

        //when
        int rowCount = placeSearchKeywordRepository.plusCount(keyword.getKeyword());

        //then
        Optional<PlaceSearchKeyword> optional = placeSearchKeywordRepository.findById(keyword.getKeyword());
        assertEquals(1, rowCount);
        assertEquals(2, optional.orElseGet(PlaceSearchKeyword::new).getCount());
    }
}