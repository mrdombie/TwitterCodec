package generation;

import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class Generator {

	public static void main(String[] args) {

		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("Mary");
		p.setVerb("dance");
		p.setObject("the monkey");

		String output2 = realiser.realiseSentence(p); // Realiser created earlier
		System.out.println(output2);


	}

}
