package jy.study;

import jy.study.place.domain.PlaceSearchKeyword;
import jy.study.place.infra.repository.PlaceSearchKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
public class SpringDataJpaConfigTest {

    @Autowired
    PlaceSearchKeywordRepository placeSearchKeywordRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("jpa 설정 확인")
    void checkConfig() {
        //given
        PlaceSearchKeyword keyword = new PlaceSearchKeyword("장소");

        //when
        PlaceSearchKeyword saved = placeSearchKeywordRepository.save(keyword);
        entityManager.flush();

        //then
        assertTrue(entityManager.contains(saved));
    }
}
