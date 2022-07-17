package jy.study.place.infra.repository;

import jy.study.place.domain.entity.PlaceSearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceSearchKeywordRepository extends JpaRepository<PlaceSearchKeyword, String> {

}
