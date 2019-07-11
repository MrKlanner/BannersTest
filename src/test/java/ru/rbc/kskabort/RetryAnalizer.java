package ru.rbc.kskabort;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static ru.rbc.kskabort.BannersTest.num_of_tests;
import static ru.rbc.kskabort.BannersTest.tests_num;
import static ru.rbc.kskabort.BannersTest.i;

class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxCount = 50;
    @Override
    public boolean retry(ITestResult result) {
        tests_num += i;
        // set your count to re-run test
        if(retryCount < maxCount && tests_num < num_of_tests/2)
        {
            retryCount++;
            return true;
        }
        return false;
    }
}
