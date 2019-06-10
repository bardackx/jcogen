package com.bardackx.jcogen.source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StringUtil {

	public static String capitalize(String s) {
		if (s == null) return s;
		if (s.trim().isEmpty()) return s;
		s = s.trim();
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static List<String> splitByLineLength(String text, int maxLength) {
		List<String> lines = new ArrayList<>();
		StringBuilder b = new StringBuilder();
		try (Scanner s = new Scanner(text)) {
			int len = 0;
			while (s.hasNext()) {

				String word = s.next();

				if (len + 1 + word.length() >= maxLength) {
					lines.add(b.toString());
					len = 0;
					b.setLength(0);
				}

				if (len != 0) {
					b.append(" ");
					len++;
				}

				b.append(word);
				len += word.length();
			}
			if (len != 0) lines.add(b.toString());
		}
		return lines;
	}

	public static String generateWhiteSpaces(int len) {
		char[] c = new char[len];
		for (int i = 0; i < len; i++)
			c[i] = ' ';
		return new String(c);
	}

	public static String indent(String text, String tab, String lineEnding) {
		StringBuilder b = new StringBuilder();
		try (Scanner s = new Scanner(text)) {
			while (s.hasNextLine()) {
				b.append(tab);
				b.append(s.nextLine());
				if (s.hasNextLine()) b.append(lineEnding);
			}
		}
		return b.toString();
	}

	public static String characterSeparatedValues(String[] values, String separator) {
		
		ArrayList<String> list = new ArrayList<String>();
		for (String e : values)
			list.add(e);
		
		Iterator<String> i = list.iterator();
		StringBuilder b = new StringBuilder();
		while (i.hasNext()) {
			b.append(i.next());
			if (i.hasNext()) b.append(separator);
		}
		return b.toString();
	}

}
