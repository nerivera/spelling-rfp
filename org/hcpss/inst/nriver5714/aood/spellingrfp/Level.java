package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.*;

public class Level {
	private final Map<String, Word> words;
	
	public Level(Word[] w) {
		words = new HashMap<String, Word>();
		
		for(int i = 0; i  < w.length; i++) {
			words.put(w[i].getWord(), w[i]);
		}
	}
	
	public Level() {
		words = new HashMap<String, Word>();
	}
	 
	public void removeWord(Word word) {
		boolean contains = words.containsKey(word.getWord());
		
		if(contains) {
			words.remove(word.getWord());
			//System.out.println(word.getWord() + " has been removed!");
		} else {
			//System.out.printnln(word.getWord() + " doesn't exist in this level!");
		}
	}
	
	public void addWord(Word word) {
		boolean contains = words.containsKey(word.getWord());
		
		if (contains) {
			//System.out.println(word.getWord() + " already exists in this level!");
		} else {
			words.put(word.getWord(), word);
			//System.out.println(word.getWord() + " has been added!");
		}
	}
	
	//change spelling of respective word
	public void modifyWord(String word) {
		Iterator<Map.Entry<String, Word>> itr = words.entrySet().iterator();
		
		while(itr.hasNext()) {
			
			Map.Entry<String, Word> entry = itr.next();
			if(entry.getKey().equals(word)) {
				
				Word tempWord = new Word(word, entry.getValue().getSentence());
				words.remove(entry.getKey());
				words.put(word, tempWord);
				break;
			}
		}
	}
	public Word getNextWord() {
		int randomIndex = (int)(Math.random() * words.size());
		Iterator<Word> it = words.values().iterator();
		for(int i = 0; i < randomIndex; i++) {
			it.next();
		}
		return it.next();
	}
	
	public int size() {
		return words.size();
	}
	
	@Override
	public String toString() {
		return words.values().toString();
	}
	
}
