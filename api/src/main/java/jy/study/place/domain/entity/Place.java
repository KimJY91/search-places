package jy.study.place.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    private String name;

    private String address;

    private String roadAddress;

    /**
     * 장소 비교
     * case 1. 주소가 같음
     * case 2. 이름이 같고 주소가 비슷
     * @param place 비교할 장소 정보
     */
    public boolean checkSamePlace(Place place) {
        if (equalsAddress(place))
            return true;

        if (name.equals(place.name)) {
            return StringUtils.getLevenshteinDistance(address, place.address) < 10 ||
                    StringUtils.getLevenshteinDistance(roadAddress, place.roadAddress) < 10;
        }

        return false;
    }

    private boolean equalsAddress(Place place) {
        return address.equals(place.address) || roadAddress.equals(place.roadAddress);
    }
}
