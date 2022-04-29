package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.*;

public class Word {
	private String word;
	private String sentence;

	public Word(String word, String sentence) {
		this.word = word;
		this.sentence = sentence;
	}

	public String getWord() {
		return word;
	}

	public String getSentence() {
		return sentence;
	}

	public SubstringRange getIncorrectPortion(String attempt) {
		char[] a = this.getWord().toCharArray();
		char[] b = attempt.toCharArray();
		int c = -1, d = -1;

		for (int i = 0; i < Math.max(a.length, b.length); i++) {
			if (i >= b.length) {
				return new SubstringRange();
			} else if (i >= a.length || a[i] != b[i]) {
				c = i;
				break;
			}
		}
		//b.length check before comparison
		for (int i = 1; i <= Math.max(a.length, b.length); i++) {
			if (i > b.length) {
				return new SubstringRange();
			}else if(i > a.length || a[a.length - i] != b[b.length - i]) {
				d = b.length - i + 1;
				break;
			}
	
		}
		return new SubstringRange(c, d);
	}

	public boolean checkAttempt(String attempt) {
		if (getWord().compareTo(attempt) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return word + ": " + sentence;
	}
}
