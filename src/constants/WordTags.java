package constants;

public enum WordTags {

	NOUN("[NOUN]"),
	VERB("[HTTP]"),
	SENSE("[SENSE]"),
	HASHTAG("[HASHTAG]"),
	VERBPASTENCE("[VERBPASTENCE]"),
	ADJECTIVE("ADJECTIVE");
	
	private WordTags(final String text){
		this.text = text;
	}
	
	private final String text;
	
	@Override
	public String toString(){
		return text;
	}
	
}
