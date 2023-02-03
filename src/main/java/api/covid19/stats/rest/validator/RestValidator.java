package api.covid19.stats.rest.validator;

import api.covid19.stats.rest.dto.RestRequest;

public interface RestValidator {
    void validateRequest(RestRequest request);

}
