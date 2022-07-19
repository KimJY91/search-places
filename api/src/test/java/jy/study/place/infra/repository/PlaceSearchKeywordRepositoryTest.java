package jy.study.place.infra.repository;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
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

    @Test
    @DisplayName("카운트가 가장 높은 10개 데이터 조회")
    void findCountTop10() {
        //given
        for (int i = 0; i < 100; i++)
            entityManager.persist(new PlaceSearchKeyword("테스트"+i, i));

        //when
        List<PlaceSearchKeyword> list = placeSearchKeywordRepository.findTop10ByOrderByCountDesc();

        //then
        assertEquals(10, list.size());
        assertEquals(99, list.get(0).getCount());
        assertThat(list).extracting("count")
                .containsExactly(99L, 98L, 97L, 96L, 95L, 94L, 93L, 92L, 91L, 90L);
    }

}