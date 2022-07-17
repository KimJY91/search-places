package jy.study.place.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "place_search_keyword")
@EqualsAndHashCode(of = "keyword")
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchKeyword {

    @Id
    private String keyword;

    private long count;

    public PlaceSearchKeyword(String keyword) {
        this.keyword = keyword;
    }
}
