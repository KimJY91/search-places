package jy.study.place.infra.repository;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceSearchKeywordRepository extends JpaRepository<PlaceSearchKeyword, String> {

    @Modifying
    @Query(value = "update PlaceSearchKeyword psk set psk.count = psk.count + 1 where psk.keyword = :keyword")
    int plusCount(@Param("keyword") String keyword);
}
