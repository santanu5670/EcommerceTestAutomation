package Org.Utilities.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {
    private SoftAssert Assert = new SoftAssert();
    public void IsTrue(boolean actualValue,String errorMessage){
        try {
            Assert.assertTrue(actualValue);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsTrue(boolean actualValue, String message, String errorMessage){
        try {
            Assert.assertTrue(actualValue,message);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsFalse(boolean actualValue, String errorMessage){
        try {
            Assert.assertFalse(actualValue);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsFalse(boolean actualValue, String message, String errorMessage){
        try {
            Assert.assertFalse(actualValue,message);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsEquals(Object actualValue, Object expectedValue, String errorMessage){
        try {
            Assert.assertEquals(actualValue,expectedValue);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsEquals(Object actualValue, Object expectedValue, String message, String errorMessage){
        try {
            Assert.assertEquals(actualValue,expectedValue,message);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsNotEquals(Object actualValue, Object expectedValue, String errorMessage){
        try {
            Assert.assertNotEquals(actualValue,expectedValue);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsNotEquals(Object actualValue, Object expectedValue, String message, String errorMessage){
        try {
            Assert.assertNotEquals(actualValue,expectedValue,message);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsNull(Object object, String errorMessage){
        try {
            Assert.assertNull(object);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsNull(Object object, String message, String errorMessage){
        try {
            Assert.assertNotEquals(object,message);
        } catch (AssertionError ex){
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsGreater(int actualValue, int expectedValue, String errorMessage){
        try {
            Assert.assertTrue(actualValue>expectedValue);
        } catch (AssertionError ex){
            System.out.println("Actual Value: "+actualValue);
            System.out.println("Expected Value: "+expectedValue);
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void IsGreater(int actualValue, int expectedValue, String message, String errorMessage){
        try {
            Assert.assertTrue(actualValue>expectedValue, message);
        } catch (AssertionError ex){
            System.out.println("Actual Value: "+actualValue);
            System.out.println("Expected Value: "+expectedValue);
            throw new AssertionError(errorMessage, ex);
        }
    }

    public void assertAll(){
        Assert.assertAll();
    }

}
