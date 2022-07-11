package org.main;

import numberrangesummarizer.NumberRangeSummarizerImplementation;

import java.util.Scanner;

/**
 * @author Bheki Ndhlovu
 *
 * A simple command line application that will summarize a string of numbers into ranges
 */
public class Main {
    public static void main(String[] args) {
        NumberRangeSummarizerImplementation summerizer = new NumberRangeSummarizerImplementation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a comma separated list of numbers: ");
        System.out.println("Result: \n" + summerizer.summarizeCollection(summerizer.collect(scanner.next())));
    }
}
