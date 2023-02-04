package api.covid19.stats.graphql.validator;

import api.covid19.stats.exceptions.InvalidRequestRtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatisticsValidatorTest {

    StatisticsValidator statisticsValidator;

    @BeforeEach
    void setUp() {
        statisticsValidator = new StatisticsValidator();
    }

    @Test
    void validateDateFailed() {
        // exec
        Assertions.assertThrows(InvalidRequestRtException.class, () -> {
            statisticsValidator.validateDate("2022-12-12");
        });
        Assertions.assertThrows(InvalidRequestRtException.class, () -> {
            statisticsValidator.validateDate("asdasd");
        });
    }

    @Test
    void validateEmptyDateFailed() {
        // exec
        Assertions.assertThrows(InvalidRequestRtException.class, () -> {
            statisticsValidator.validateDate(" ");
        });
        Assertions.assertThrows(InvalidRequestRtException.class, () -> {
            statisticsValidator.validateDate(null);
        });
    }

    @Test
    void validateDateOk() {
        // exec
        statisticsValidator.validateDate("2022-12-12T00:00:00");
    }

    @Test
    void validateCountries() {
        // asser
        Assertions.assertThrows(InvalidRequestRtException.class,
                                () -> statisticsValidator.validateCountries(null),
                                "Empty Country."
        );
    }
}
