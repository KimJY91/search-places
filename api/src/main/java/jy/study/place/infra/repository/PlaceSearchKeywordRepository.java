package jy.study.place.infra.repository;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceSearchKeywordRepository extends JpaRepository<PlaceSearchKeyword, String> {

    @Transactional
    @Modifying
    @Query(value = "update PlaceSearchKeyword psk set psk.count = psk.count + 1 where psk.keyword = :keyword")
    int plusCount(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(value = "insert into place_search_keyword (keyword, count) values (:keyword, 0)", nativeQuery = true)
    void init(@Param("keyword") String keyword);

    List<PlaceSearchKeyword> findTop10ByOrderByCountDesc();
}
