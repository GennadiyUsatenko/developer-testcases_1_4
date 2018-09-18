package com.paazl.cases.test2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.paazl.cases.common.Constants.QUESTIONS;

public class Main {
    /*
     * TODO
     * Implement a main method that is functionally identical to Test #1. In
     * this case, use a DeveloperFactory that is able to produce 3 types of
     * developers that implement a "print" method. The Factory should have a
     * create method that takes the user's score as an argument.
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

            DeveloperFactory.create(score).print();
        }
    }

    private static class DeveloperFactory {
        public static Developer create(long score) {
            Developer developer;

            if(score < 4) developer = new JuniorDeveloper();
            else if(score < 8) developer = new MiddleDeveloper();
            else developer = new SeniorDeveloper();

            return developer;
        }

        private static abstract class Developer {
            public abstract void print();
        }

        private static class JuniorDeveloper extends Developer {
            @Override
            public void print() {
                System.out.println("You are a junior Java developer");
            }
        }
        private static class MiddleDeveloper extends Developer {
            @Override
            public void print() {
                System.out.println("You are a medior Java developer");
            }
        }
        private static class SeniorDeveloper extends Developer {
            @Override
            public void print() {
                System.out.println("You are a senior Java developer");
            }
        }
    }
}