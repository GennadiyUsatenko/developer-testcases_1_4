package com.paazl.cases.test3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.paazl.cases.common.Constants.QUESTIONS;

public class Main {
    /*
     * TODO
     * Implement a main method that is functionally identical to Test #2. In
     * this case, use a Singleton that poses the questions and gathers the answers.
     */
    public static void main(String[] args) {
        Map<String, Character> answers = new HashMap<>();
        try(Scanner scanner = new Scanner(System.in)) {
            long score = QUESTIONS.entrySet().stream()
                    .filter(e -> {
                        System.out.println(e.getKey());
                        while (!scanner.hasNext(Pattern.compile("[nyNY]"))){
                            scanner.next();
                        }
                        answers.put(e.getKey(), scanner.next().toUpperCase().charAt(0));
                        return e.getValue().equals(answers.get(e.getKey()));
                    })
                    .count();

            Singleton.create(score).print();
        }
    }

    private static class Singleton{
        private static final Developer juniorDeveloper = new JuniorDeveloper();
        private static final Developer middleDeveloper = new MiddleDeveloper();
        private static final Developer seniorDeveloper = new SeniorDeveloper();

        public static Developer create(long score){
            if(score < 4) return juniorDeveloper;
            if(score < 8) return middleDeveloper;
            else return seniorDeveloper;
        }

        private static abstract class Developer{
            public abstract void print();
        }

        private static class JuniorDeveloper extends Developer{
            @Override
            public void print() {
                System.out.println("You are a junior Java developer");
            }
        }
        private static class MiddleDeveloper extends Developer{
            @Override
            public void print() {
                System.out.println("You are a medior Java developer");
            }
        }
        private static class SeniorDeveloper extends Developer{
            @Override
            public void print() {
                System.out.println("You are a senior Java developer");
            }
        }
    }
}