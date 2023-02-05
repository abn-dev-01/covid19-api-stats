package api.covid19.stats.cov19data.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {
    private static final String NO = "no";
    public static final CountryDto EMPTY = new CountryDto(NO, NO, NO);

    @JsonProperty("country")
    private String countryName;
    private String slug;
    private String iso2;
}
