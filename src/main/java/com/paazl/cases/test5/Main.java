package com.paazl.cases.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
	/*
	 * TODO This class bombs an Account object with a number of simultaneous
	 * deposits. The resulting balance should be equal to the sum of the
	 * deposit, but the current implementation has some concurrency issues. Your
	 * task: without touching this Main class, fix these issues.
	 */
	public static void main(String[] args) throws Exception {
		Account account = new Account();
		
		// Generate a number of random deposits
		List<Integer> deposits = new Random().ints(0, 1000).limit(1000).boxed().collect(Collectors.toList());
		
		System.out.printf("Depositing these amounts to account: %s\n", deposits);
		
		// Bomb the account with these deposits
		depositConcurrently(account, deposits);
		
		int expectedBalance = deposits.stream().mapToInt(i -> i).sum();
		int actualBalance = account.getBalance().intValue();
		
		if (expectedBalance != actualBalance)
			System.err.printf("Expected balance %d, got %d", actualBalance, expectedBalance);
		else
			System.out.printf("Successfully made %d deposits!", deposits.size());
	}

	private static void depositConcurrently(Account account, List<Integer> deposits) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		List<Callable<Object>> tasks = new ArrayList<>(deposits.size());
		for (Integer deposit : deposits)
			tasks.add(Executors.callable(() -> account.deposit(deposit)));
		
		executor.invokeAll(tasks);
		
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}
}