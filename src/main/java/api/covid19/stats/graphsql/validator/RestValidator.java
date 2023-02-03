package api.covid19.stats.graphsql.validator;

import api.covid19.stats.graphsql.dto.RestRequest;

public interface RestValidator {
    void validateRequest(RestRequest request);

}
