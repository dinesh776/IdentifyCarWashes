package reRun;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * RetryListener is a TestNG listener that automatically applies the RetryAnalyzer
 * to all test methods without needing to annotate each test individually.
 */
public class RetryListener implements IAnnotationTransformer {

    /**
     * This method is invoked by TestNG to modify the behavior of test annotations at runtime.
     * It sets the retry analyzer for each test method to RetryAnalyzer.
     */
    @Override
    public void transform(ITestAnnotation annotation,
                          @SuppressWarnings("rawtypes") Class testClass,
                          @SuppressWarnings("rawtypes") Constructor constructor,
                          Method method) {
        // Set the retry analyzer for all test methods
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}

