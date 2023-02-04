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
    private String cityCode;
    private int recovered;
    private String comment;
    private String lon;
    private String city;
    private String province;
    private String date;
    private int active;
    private String country;
    private int deaths;
    private int confirmed;
    private String countryCode;
    private String lat;
}
