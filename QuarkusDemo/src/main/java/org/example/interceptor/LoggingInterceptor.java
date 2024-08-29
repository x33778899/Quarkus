package org.example.interceptor;

import io.quarkus.agroal.DataSource;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.example.service.LoggingService;

import java.util.Arrays;
import java.util.logging.Logger;

@Loggable
@Interceptor
public class LoggingInterceptor {


    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class.getName());

    @Inject
    private LoggingService loggingService;

    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {
        String methodName = context.getMethod().getName();
        String enteringMessage = "Entering method: " + methodName;
        String exitingMessage = "Exiting method: " + methodName;
        String parameters = "Parameters: " + Arrays.toString(context.getParameters()); // Include method parameters

        // Log entering message
        LOGGER.info(enteringMessage + " " + parameters);
        loggingService.log(methodName, enteringMessage + " " + parameters);

        long startTime = System.currentTimeMillis();
        try {
            // Proceed with the method invocation
            Object result = context.proceed();
            return result;
        } catch (Exception e) {
            // Log the exception
            String errorMessage = "Exception in method: " + methodName + " Error: " + e.getMessage();
            LOGGER.severe(errorMessage);
            loggingService.log(methodName, errorMessage);
            throw e; // Re-throw the exception after logging it
        } finally {
            // Log exiting message with execution time
            long executionTime = System.currentTimeMillis() - startTime;
            String executionTimeMessage = "Execution time: " + executionTime + "ms";
            LOGGER.info(exitingMessage + " " + executionTimeMessage);
            loggingService.log(methodName, exitingMessage + " " + executionTimeMessage);
        }
    }
}