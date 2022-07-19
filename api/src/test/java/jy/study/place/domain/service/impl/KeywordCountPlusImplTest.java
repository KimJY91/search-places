package jy.study.place.domain.service.impl;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class KeywordCountPlusImplTest {

    @Autowired
    KeywordCountPlusImpl keywordCountPlus;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("카운트 증가 - 데이터 등록되어 있음")
    void plusCount() {
        //given
        String keyword = "테스트1";
        entityManager.persist(new PlaceSearchKeyword(keyword, 10));
        entityManager.flush();
        entityManager.clear();

        //when
        keywordCountPlus.plusCount(keyword);

        //then
        PlaceSearchKeyword placeSearchKeyword = entityManager.find(PlaceSearchKeyword.class, keyword);
        assertEquals(keyword, placeSearchKeyword.getKeyword());
        assertEquals(11, placeSearchKeyword.getCount());
    }


    @Test
    @DisplayName("카운트 증가 - 데이터 등록되지 않음")
    void emptyDataPlusCount() {
        //given
        String keyword = "테스트2";

        //when
        keywordCountPlus.plusCount(keyword);
        entityManager.flush();
        entityManager.clear();

        //then
        PlaceSearchKeyword placeSearchKeyword = entityManager.find(PlaceSearchKeyword.class, keyword);
        assertEquals(keyword, placeSearchKeyword.getKeyword());
        assertEquals(1, placeSearchKeyword.getCount());
    }

    @Test
    @DisplayName("다중 스레드로 카운트 증가")
    void multiThread() throws InterruptedException {
        //given
        String keyword = "다중스레드 테스트";
        int numberOfThreads = 10000;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        //when
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                keywordCountPlus.plusCount(keyword);
                latch.countDown();
            });
        }
        latch.await();

        //then
        PlaceSearchKeyword placeSearchKeyword = entityManager.find(PlaceSearchKeyword.class, keyword);
        assertEquals(numberOfThreads, placeSearchKeyword.getCount());
    }

}