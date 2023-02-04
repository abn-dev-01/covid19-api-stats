package api.covid19.stats.cov19data.api.dto;

import lombok.Data;

@Data
public class PremiumCountryAllStatusResponse {
	private String countryISO;
	private int newDeaths;
	private int dailyIncidenceConfirmedCasesPerMillion;
	private Object totalCasesPerMillion;
	private int stringencyIndex;
	private Object newCasesPerMillion;
	private int incidenceRiskNewDeathsPerHundredThousand;
	private int totalCases;
	private int dailyIncidenceConfirmedDeaths;
	private int caseFatalityRatio;
	private String date;
	private String continent;
	private int incidenceRiskNewConfirmedPerHundredThousand;
	private int incidenceRiskConfirmedPerHundredThousand;
	private int newDeathsPerMillion;
	private String country;
	private int totalDeathsPerMillion;
	private String iD;
	private int dailyIncidenceConfirmedCases;
	private int newCases;
	private int dailyIncidenceConfirmedDeathsPerMillion;
	private int totalDeaths;
	private int incidenceRiskDeathsPerHundredThousand;
}
