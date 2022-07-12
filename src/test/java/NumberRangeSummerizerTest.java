import com.sun.org.glassfish.gmbal.Description;
import numberrangesummarizer.NumberRangeSummarizerImplementation;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Bheki Ndhlovu
 *
 * This class contains several unit tests for the purpose of testing methods in NumberRangeSummarizerImplementation
 */
public class NumberRangeSummerizerTest {
    private NumberRangeSummarizerImplementation numberRangeSummarizer = new NumberRangeSummarizerImplementation();

    @DisplayName("Check Collection Size")
    @Description("This test will ensure that the correct collection size is returned based on the number of integers in the string")
    @Test
    public void correctCollectionSize() {
       assertEquals(numberRangeSummarizer.collect("1,2,3").size(), 3);
    }

    @DisplayName("Check collection contains integers")
    @Description("This test will ensure that the collection is not empty and actually contains integers")
    @Test
    public void numberArrayIsNotEmpty() {
        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertFalse(numbers.isEmpty());
        Assert.assertEquals(numbers.iterator().hasNext(), true);
    }

    @DisplayName("Check collection is sequential")
    @Description("This test will ensure that the collection is sequential")
    @Test
    public void collectionIsSequential() {
        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertFalse(Boolean.parseBoolean(Arrays.stream(numbers.toArray()).unordered().toString()));
    }

    @DisplayName("Check that strings with duplicates are summarized")
    @Description("This test will ensure that a normal string of comma delimited of numbers in ascending order is summarized")
    @Test
    public void normalInputWithDuplicates() {
        String inputNumbers = "1,3,3,3,3,3,3,3,3,3,3,6,6,6,6,7,8,12,13,14,14,14,15,21,21,21,22,22,22,22,22,22,23,23,23,23,23,23,24,31";
        String expectedSummary = "1,3,6-8,12-15,21-24,31";
        Collection<Integer> numbers = numberRangeSummarizer.collect(inputNumbers);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedSummary);
    }

    @DisplayName("Check normal sequential numbers are summarized")
    @Description("This test will ensure that a normal string of comma delimited of numbers in ascending order is summarized")
    @Test
    public void normalSequentialNumbersAreSummarized() {
        String inputNumbers = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedSummary = "1,3,6-8,12-15,21-24,31";
        Collection<Integer> numbers = numberRangeSummarizer.collect(inputNumbers);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedSummary);
    }

    @DisplayName("Check non sequential strings are summarized")
    @Description("This test will ensure that strings with no sequential numbers inputted will return the same string")
    @Test
    public void nonSequentialNumberStringsAreSummarized() {
        String inputNumbers = "1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31";
        String expectedSummary = "1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31";
        Collection<Integer> numbers = numberRangeSummarizer.collect(inputNumbers);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedSummary);
    }

    @DisplayName("Check that Negative Strings are summarized")
    @Description("This test will ensure that strings with negative numbers are summarized")
    @Test
    public void negativeSequentialNumbersAreSummarized() {
        StringBuilder sb = new StringBuilder();
        String expectedSummary = "-10-0";
        for (int i = -10; i <= 0; i++) {
            sb.append(i);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(sb.toString())),expectedSummary);
    }

    @DisplayName("Check that large sequential strings are summarized")
    @Description("This test will ensure that large strings will be summarized efficiently")
    @Test
    public void largerSequentialStringsAreSummarized() {
        StringBuilder sb = new StringBuilder();
        String expectedSummary = "1-1000";
        for (int i = 1; i <= 1000; i++) {
            sb.append(i);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(sb.toString())),expectedSummary);
    }

    @DisplayName("Check that short strings are summarized")
    @Description("This test will ensure that short strings comprising of two elements are summarized")
    @Test
    public void shortSequentialStringsAreSummarized() {
        String input = "1,2";
        String expectedSummary = "1-2";
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(input)),expectedSummary);
    }

    @DisplayName("Check that IllegalArgumentException is thrown for empty string")
    @Description("This test will ensure that is a empty string is collected the correct exception will be thrown")
    @Test(expected=IllegalArgumentException.class)
    public void collectEmptyString() throws IllegalArgumentException{
        Collection<Integer> actual = numberRangeSummarizer.collect("");
    }

    @DisplayName("Check that special characters in string throw exception")
    @Description("This test will ensure that strings containing special characters will throw exception")
    @Test(expected=IllegalArgumentException.class)
    public void incorrectFormat() throws IllegalArgumentException{
        Collection<Integer> actual = numberRangeSummarizer.collect("1,2,3,$,6");
    }

    @DisplayName("Check that IllegalArgumentException is thrown for null input")
    @Description("This test will ensure that a null pointer exception is thrown for any ")
    @Test(expected=IllegalArgumentException.class)
    public void nullInput() throws IllegalArgumentException {
        Collection<Integer> actual = numberRangeSummarizer.collect(null);
    }

}
