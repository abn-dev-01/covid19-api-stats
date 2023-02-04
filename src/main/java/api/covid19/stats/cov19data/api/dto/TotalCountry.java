package api.covid19.stats.cov19data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalCountry {
    private String country;
    private String countryCode;
    private String province;
    private String city;
    private String cityCode;
    private String lat;
    private String lon;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private String date;
    private String comment;
}
