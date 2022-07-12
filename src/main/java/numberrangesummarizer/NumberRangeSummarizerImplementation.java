package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bheki Ndhlovu
 * <p>
 * This class will implement the two methods from the NumberRangeSummerizer interface.
 */
public class NumberRangeSummarizerImplementation
        implements NumberRangeSummarizer {

    private static final Pattern pattern = Pattern.compile("-?[0-9]+(,-?[0-9]+)+");
    private Matcher matcher;

    /**
     * @author Bheki Ndhlovu
     * <p>
     * This method will accept a comma delineated string of integer.
     * It will then process the string into a collection and return a collection of integers.
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input != null && pattern.matcher(input).matches()) {
            return Arrays.stream(input.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("invalid input");
    }

    /**
     * @author Bheki Ndhlovu
     * <p>
     * This method will accept a collection of integers.
     * It will then sort the array and collapse sequential numbers into ranges.
     * It will finally parse the array into a string and re
     */
    public String summarizeCollection(Collection<Integer> input) {
        Integer[] arr = input.stream().sorted().distinct().collect(toCollection(ArrayList::new)).toArray(new Integer[0]);
        Integer end, start;
        end = start = arr[0];
        StringBuilder result = new StringBuilder("");
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].equals(arr[i - 1] + 1)) {
                end = arr[i];
            } else {
                if (start.equals(end)) {
                    result.append(start).append(",");
                } else if (end.equals(start + 1)) {
                    result.append(start).append(",").append(end).append(",");
                } else {
                    result.append(start).append("-").append(end).append(",");
                }
                start = end = arr[i];
            }
        }
        if (start.equals(end)) {
            result.append(start);
        } else {
            result.append(start).append("-").append(end);
        }
        return result.toString();
    }
}
