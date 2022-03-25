package org.hcpss.inst.nriver5714.aood.spellingrfp;

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
	int[] getIncorrectPortion(String attempt) {
		
	}
	public boolean checkAttempt(String attempt) {
		if(getWord().compareTo(attempt) == 0) {
			return true;
		}
		return false;
	}
}
