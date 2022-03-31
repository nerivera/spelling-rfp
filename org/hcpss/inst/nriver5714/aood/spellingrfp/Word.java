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

	public int[] getIncorrectPortion(String attempt) {
		char[] a = this.getWord().toCharArray();
		char[] b = attempt.toCharArray();
		int[] c = new int[2];
		
		for (int i = 0; i < Math.max(a.length, b.length); i++) {
			if(i >= a.length || a[i] != b[i]) {
				c[0] = i;
				break;
			} else if(i >= b.length) {
				return null;
			}
		}
		
		for(int i = 1; i <= Math.max(a.length, b.length); i++) {
			if(a[a.length - i] != b[b.length - i]) {
				c[1] = i;
				break;
			}
		}

		return c;
	}

	public boolean checkAttempt(String attempt) {
		if (getWord().compareTo(attempt) == 0) {
			return true;
		}
		return false;
	}
}
