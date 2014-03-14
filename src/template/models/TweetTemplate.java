package template.models;

import java.util.List;

public class TweetTemplate {

	String body;
	List<String> tags;
	
	public String[] getListBody() {
		return body.split(" ");
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
