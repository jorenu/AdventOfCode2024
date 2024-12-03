package be.outputter.aoc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    private static final Pattern MULTIPLY_REGEX = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
    private static final Pattern DONT_DO_REGEX = Pattern.compile("don't\\(\\).*?do\\(\\)");
    private static final Pattern DONT_REGEX = Pattern.compile("(don't\\(\\).*$)");

    public static void main(String[] args) {
        new Day03().start();
    }

    private void start() {
        List<String> lines = InputLoader.getInput("day03.txt");
        String input = String.join("", lines);

        partOne(input);
        partTwo(input);
    }

    private void partOne(String input) {
        System.out.println(calculateMultiply(input));
    }

    private void partTwo(String input) {
        Matcher dontDoMatcher = DONT_DO_REGEX.matcher(input);
        while (dontDoMatcher.find()) {
            String dontString = dontDoMatcher.group();
            input = input.replace(dontString, "");
        }

        Matcher dontMatcher = DONT_REGEX.matcher(input);
        while (dontMatcher.find()) {
            input = input.replace(dontMatcher.group(), "");
        }

        System.out.println(calculateMultiply(input));
    }

    private int calculateMultiply(String input) {
        int result = 0;

        Matcher matcher = MULTIPLY_REGEX.matcher(input);
        while (matcher.find()) {
            int number1 = Integer.parseInt(matcher.group(1));
            int number2 = Integer.parseInt(matcher.group(2));
            result += number1 * number2;
        }

        return result;
    }

}
