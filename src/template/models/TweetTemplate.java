package template.models;

import java.util.List;

public class TweetTemplate {

	String body;
	
	public String[] getListBody() {
		return body.split(" ");
	}

	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
}
