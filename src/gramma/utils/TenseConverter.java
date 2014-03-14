package gramma.utils;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class TenseConverter {

	private String setup(String verb, String feature, Tense tense){
	
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);       
		SPhraseSpec p = nlgFactory.createClause();
		Realiser realiser = new Realiser(lexicon);
		p.setVerb(verb);
		p.setFeature(feature, tense);
		String word = realiser.realiseSentence(p);
		
		return word;
	}
	
	public String setFuturetence(String verb){
		String word = setup(verb, Feature.TENSE, Tense.FUTURE);
		return word;
	}
	
	public String setPastTence(String verb){
		String word = setup(verb, Feature.TENSE, Tense.PAST);
		return word;
	}
}
