package com.zimblesystems.model;

public class TestObject {

    private String testString;

    public TestObject(String testString) {
        this.testString = testString;
    }


    public String getTestString() {
        return testString;
    }

    public TestObject() {
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "testString='" + testString + '\'' +
                '}';
    }
}
