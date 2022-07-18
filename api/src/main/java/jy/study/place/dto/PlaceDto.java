package jy.study.place.dto;

import jy.study.place.domain.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {

    private String name;

    private String address;

    private String roadAddress;

    public PlaceDto(Place place) {
        this.name = place.getName();
        this.address = place.getAddress();
        this.roadAddress = place.getRoadAddress();
    }
}
