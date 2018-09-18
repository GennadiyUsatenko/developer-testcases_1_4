package com.paazl.cases.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.paazl.cases.common.Constants.QUESTIONS;

public class Main {
	/*
	 * TODO
	 * Implement the main method:
	 * * Show the user the questions from Constants.QUESTIONS on a console and collect the answers.
	 * * If the user scores 0-3 points, print "You are a junior Java developer".
	 * * If the user scores 4-7 points, print "You are a medior Java developer".
	 * * If the user scores 8-10 points, print "You are a senior Java developer".
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

            if(score < 4) System.out.println("You are a junior Java developer");
            else if(score < 8) System.out.println("You are a medior Java developer");
            else System.out.println("You are a senior Java developer");
        }
	}
}