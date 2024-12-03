package be.outputter.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        new Day02().start();
    }

    private void start() {
        List<List<Integer>> reports = InputLoader.getInput("day02.txt").stream()
                .map(s -> Arrays.stream(s.split(" ")).map(Integer::parseInt).toList())
                .toList();

        List<ReportRule> rules = List.of(
                new IncreaseOrDecreaseRule(),
                new DifferAtLeast1AndAtMost3Rule()
        );

        partOne(reports, rules);
        partTwo(reports, rules);
    }

    private void partOne(List<List<Integer>> reports, List<ReportRule> rules) {
        int amountSafe = 0;

        for (List<Integer> report : reports) {
            boolean safe = rules.stream().allMatch(rule -> rule.apply(report));
            if (safe) {
                amountSafe++;
            }
        }

        System.out.println(amountSafe);
    }

    private void partTwo(List<List<Integer>> reports, List<ReportRule> rules) {
        int amountSafe = 0;

        for (List<Integer> report : reports) {
            boolean safe = rules.stream().allMatch(rule -> rule.apply(report));

            for (int i = 0; i < report.size(); i++) {
                List<Integer> trimmedReport = new ArrayList<>(report);
                trimmedReport.remove(i);
                if (rules.stream().allMatch(rule -> rule.apply(trimmedReport))) {
                    safe = true;
                }
            }

            if (safe) {
                amountSafe++;
            }
        }

        System.out.println(amountSafe);
    }

}

interface ReportRule {
    boolean apply(List<Integer> report);
}

class IncreaseOrDecreaseRule implements ReportRule {

    @Override
    public boolean apply(List<Integer> report) {
        boolean trend = report.getFirst() < report.get(1);
        int previous = report.getFirst();

        for (int i = 1; i < report.size(); i++) {
            boolean increasing = report.get(i) > previous;
            if (increasing != trend) {
                return false;
            }
            previous = report.get(i);
        }
        return true;
    }
}

class DifferAtLeast1AndAtMost3Rule implements ReportRule {

    @Override
    public boolean apply(List<Integer> report) {
        int previous = report.getFirst();
        for (int i = 1; i < report.size(); i++) {
            int current = report.get(i);
            int diff = Math.abs(current - previous);

            if (diff < 1 || diff > 3) return false;

            previous = current;
        }
        return true;
    }
}
