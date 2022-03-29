package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.util.*;

public class Level {
	private final Set<Word> words;
	private int lvl;
	
	public Level(Word[] w, int lvl) {
		words = new HashSet<Word>();
		
		for(int i = 0; i  < w.length; i++) {
			words.add(w[i]);
		}
		
		this.lvl = lvl;
	}
	
	public void removeWord(Word word) {
		boolean remove = words.remove(word);
		
		if(remove) {
			//System.out.println(word.getWord() + " has been removed");
		}
		else {
			//System.out.println(word.getWord() + " doesn't exist in this level");
		}
	}
	
	public void addWord(Word word) {
		boolean add = words.add(word);
		
		if(add) {
			//System.out.println(word.getWord() + " has been added to the level");
		} else {
			//System.out.println(word.getWord() + " already exists within this level);
		}
	}
	
	//change spelling of respective word
	public void modifyWord(String word) {
		Iterator<Word> i = words.iterator();
		while (i.hasNext()) {
			
			String wordReplaced = i.next().getWord().toLowerCase();
			
			if(wordReplaced.equals(word.toLowerCase())) {
				Word w = new Word(word, i.next().getSentence());
				words.remove(wordReplaced);
				words.add(w);
				break;
			}
		}
	}
}
