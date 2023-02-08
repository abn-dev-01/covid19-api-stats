package api.covid19.stats.exceptions;

import api.covid19.stats.cov19data.exception.Covid19ApiRtException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof Covid19ApiRtException) {
            return GraphqlErrorBuilder.newError()
                                      .errorType(ErrorType.INTERNAL_ERROR)
                                      .message(ex.getMessage())
                                      .path(env.getExecutionStepInfo().getPath())
                                      .location(env.getField().getSourceLocation())
                                      .build();
        } else if (ex instanceof InvalidParameterRtException) {
            return GraphqlErrorBuilder.newError()
                                      .errorType(ErrorType.BAD_REQUEST)
                                      .message(ex.getMessage())
                                      .path(env.getExecutionStepInfo().getPath())
                                      .location(env.getField().getSourceLocation())
                                      .build();
        } else if (ex instanceof InvalidRequestRtException) {
            return GraphqlErrorBuilder.newError()
                                      .errorType(ErrorType.BAD_REQUEST)
                                      .message(ex.getMessage())
                                      .path(env.getExecutionStepInfo().getPath())
                                      .location(env.getField().getSourceLocation())
                                      .build();
        } else {
            return null;
        }
    }
}
