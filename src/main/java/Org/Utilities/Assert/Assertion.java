package Org.Utilities.Assert;

import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Assertion {
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

    public <T> Boolean compareListAndPrintDifferences(List<T> beforeList, List<T> afterList){
        if(beforeList==null && afterList==null){
            System.out.println("Both list are null and equal");
            return true;
        }
        if(beforeList==null || afterList==null){
            System.out.println("One list is null and they are not equal");
            return false;
        }

        System.out.println("Expected List: "+beforeList);
        System.out.println("Actual List: "+afterList);

        boolean areEqual = true;
        if(beforeList.size()!=afterList.size()){
            System.out.println("List size are different. Expected Size: " + beforeList.size() + ", Actual Size: " + afterList.size());
            return false;
        }

        for(int i = 0; i < beforeList.size(); i++){
            T before = beforeList.get(i);
            T after = afterList.get(i);
            areEqual = areEqual&&compareAndPrintDifference(before, after);
        }

        if(areEqual){
            System.out.println("Both list are equal");
        }else{
            System.out.println("Both list aree different");
        }
        return areEqual;
    }

    public <T> Boolean compareListAndPrintDifferences(List<T> beforeList, List<T> afterList,String Excludes){
        if(beforeList==null && afterList==null){
            System.out.println("Both list are null and equal");
            return true;
        }
        if(beforeList==null || afterList==null){
            System.out.println("One list is null and they are not equal");
            return false;
        }

        System.out.println("Expected List: "+beforeList);
        System.out.println("Actual List: "+afterList);

        boolean areEqual = true;
        if(beforeList.size()!=afterList.size()){
            System.out.println("List size are different. Expected Size: " + beforeList.size() + ", Actual Size: " + afterList.size());
            return false;
        }

        for(int i = 0; i < beforeList.size(); i++){
            T before = beforeList.get(i);
            T after = afterList.get(i);
            areEqual = areEqual&&compareAndPrintDifference(before, after,Excludes);
        }

        if(areEqual){
            System.out.println("Both list are equal");
        }else{
            System.out.println("Both list aree different");
        }
        return areEqual;
    }

    public <T> Boolean compareAndPrintDifference(T Before, T After) {
        if(Before==null && After==null){
            System.out.println("Both are null and equal");
            return true;
        }
        if(Before==null || After==null){
            System.out.println("One object is null and they are not equal");
            return false;
        }
        System.out.println("Expected: "+Before);
        System.out.println("Actual: "+After);
        boolean areEqual = true;

        Class<?> clazz = Before.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
//            field.setAccessible(true);
            try {
                Object value1 = field.get(Before);
                Object value2 = field.get(After);
                if(value1 != null && value2 != null){
                    continue;
                }
                if(value1 != null || value2 != null){
                    System.out.println(field.getName()+" is different. Expected Value - " +value1+ " And Actual Value - " + value2);
                    areEqual = false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if(areEqual) {
            System.out.println("Objects are equal");
        }else{
            System.out.println("Objects are not equal");
        }
        return areEqual;
    }

    public <T> Boolean compareAndPrintDifference(T Before, T After,String Exclude) {
        List<String> ExcludeFields = Arrays.stream(Exclude.split(",")).collect(Collectors.toList());
        if(Before==null && After==null){
            System.out.println("Both are null and equal");
            return true;
        }
        if(Before==null || After==null){
            System.out.println("One object is null and they are not equal");
            return false;
        }
        System.out.println("Expected: "+Before);
        System.out.println("Actual: "+After);
        boolean areEqual = true;

        Class<?> clazz = Before.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if(ExcludeFields.contains(field.getName())){
                field.setAccessible(true);
                try {
                    Object value1 = field.get(Before);
                    Object value2 = field.get(After);
                    if(value1 != null && value2 != null){
                        continue;
                    }
                    if(value1 != null || value2 != null){
                        System.out.println(field.getName()+" is different. Expected Value - " +value1+ " And Actual Value - " + value2);
                        areEqual = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if(areEqual) {
            System.out.println("Objects are equal");
        }else{
            System.out.println("Objects are not equal");
        }
        return areEqual;
    }
}
