import numberrangesummarizer.NumberRangeSummarizerImplementation;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * @author Bheki Ndhlovu
 *
 * This class contains several unit tests for the purpose of testing methods in NumberRangeSummarizerImplementation
 */
public class NumberRangeSummerizerTest {
    private NumberRangeSummarizerImplementation numberRangeSummarizer = new NumberRangeSummarizerImplementation();

    @Test
    public void CorrectCollectionSize() {
       assertEquals(numberRangeSummarizer.collect("1,2,3").size(), 3);
    }

    @Test
    public void NumberArrayIsNotEmpty() {
        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertFalse(numbers.isEmpty());
        Assert.assertEquals(numbers.iterator().hasNext(), true);
    }

    @Test
    public void CollectionIsSequential() {
        Collection<Integer> numbers = numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertFalse(Boolean.parseBoolean(Arrays.stream(numbers.toArray()).unordered().toString()));
    }

    @Test
    public void NormalSequentialNumbersAreSummarized() {
        String inputNumbers = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedSummary = "1,3,6-8,12-15,21-24,31";
        Collection<Integer> numbers = numberRangeSummarizer.collect(inputNumbers);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedSummary);
    }

    @Test
    public void NonSequentialNumberStringsAreSummarized() {
        String inputNumbers = "1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31";
        String expectedSummary = "1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31";
        Collection<Integer> numbers = numberRangeSummarizer.collect(inputNumbers);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numbers), expectedSummary);
    }

    @Test
    public void LargerSequentialStringsAreSummarized() {
        StringBuilder sb = new StringBuilder();
        String expectedSummary = "1-1000";
        for (int i = 1; i <= 1000; i++) {
            sb.append(i);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(sb.toString())),expectedSummary);
    }

    @Test
    public void ShortSequentialStringsAreSummarized() {
        String input = "1,2";
        String expectedSummary = "1-2";
        Assert.assertEquals(numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(input)),expectedSummary);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CollectEmptyString() throws IllegalArgumentException{
        Collection<Integer> actual = numberRangeSummarizer.collect("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void IncorrectFormat() throws IllegalArgumentException{
        Collection<Integer> actual = numberRangeSummarizer.collect("1234567");
    }

    @Test(expected=IllegalArgumentException.class)
    public void NullInput() throws IllegalArgumentException {
        Collection<Integer> actual = numberRangeSummarizer.collect(null);
    }

}
