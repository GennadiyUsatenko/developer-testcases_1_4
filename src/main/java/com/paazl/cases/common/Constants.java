package com.paazl.cases.common;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Constants {
	// You'll need these questions in tests 1-4
	public static final Map<String, Character> QUESTIONS = ImmutableMap.<String, Character>builder()
			.put("A Thread is a process", 'N')
			.put("A List cannot contain duplicates", 'N')
			.put("A Set can contain duplicates", 'N')
			.put("Java is platform independent", 'Y')
			.put("Java supports copy constructors like C++", 'Y')
			.put("The primitive data types supported by the Java programming language are: byte, short, int, long, float, double, boolean, char", 'Y')
			.put("Constructor overloading is similar to method overloading in Java", 'N')
			.put("The purpose of garbage collection in Java is to reclaim and reuse objects which are no longer used", 'Y')
			.put("Autoboxing is the automatic conversion made by the Java compiler between the primitive types and their corresponding object wrapper classes", 'Y')
			.put("JDBC is an abstraction layer that allows users to choose between databases", 'Y')
			.put("Java supports the usage of pointers", 'N')
			.build();
}