package be.outputter.aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {

    public static void main(String[] args) {
        new Day01().start();
    }

    private void start() {
        List<String> input = InputLoader.getInput("day01.txt");
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();

        for (String line : input) {
            String[] split = line.split(" {3}");
            listOne.add(Integer.parseInt(split[0]));
            listTwo.add(Integer.parseInt(split[1]));
        }

        partOne(listOne, listTwo);
        partTwo(listOne, listTwo);
    }

    private void partOne(List<Integer> listOne, List<Integer> listTwo) {
        Collections.sort(listOne);
        Collections.sort(listTwo);

        int distance = 0;

        for (int i = 0; i < listOne.size(); i++) {
            distance += Math.abs(listOne.get(i) - listTwo.get(i));
        }

        System.out.println(distance);
    }

    private void partTwo(List<Integer> listOne, List<Integer> listTwo) {
        int similarity = 0;

        for (Integer i : listOne) {
            int frequency = Collections.frequency(listTwo, i);
            similarity += frequency * i;
        }

        System.out.println(similarity);
    }

}
