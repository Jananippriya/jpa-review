package io.pragra.jpareview;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class JUnitTestSuite {
    public static void main(String[] a) {
        // add the test's in the suite
        TestSuite suite = new TestSuite(TestJUnit.class, TestJUnit2.class, TestJUnit3.class );
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("Number of test cases = " + result.runCount());
    }
}
