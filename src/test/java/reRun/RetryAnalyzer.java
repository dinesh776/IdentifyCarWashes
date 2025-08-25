package reRun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer is used to automatically retry failed test cases in TestNG.
 * Implements IRetryAnalyzer interface to define custom retry logic.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    // Counter to track the number of retry attempts
    private int retryCount = 0;

    // Maximum number of retry attempts allowed
    private static final int maxRetryCount = 1; // You can customize this value

    /**
     * This method is invoked by TestNG when a test fails.
     */
    @Override
    public boolean retry(ITestResult result) {
        // Retry only if the test failed and retry count is less than max allowed
        if (!result.isSuccess() && retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying failed scenario: " + result.getName() + " | Attempt #" + retryCount);
            return true;
        }
        return false;
    }
}

