package jperkeydemo.Tests;

public abstract class TestSuite {
    protected final String suiteName;

    public TestSuite(String suiteName) {
        this.suiteName = suiteName;
    }

    // Optional: you can add hooks like beforeSuite(), afterSuite(), etc.
}
