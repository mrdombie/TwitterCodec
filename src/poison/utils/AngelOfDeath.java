package poison.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import poison.interfaces.Poison;
import template.models.TweetTemplate;

import com.jcabi.aspects.Loggable;

public class AngelOfDeath {

	protected List<String> dataToInsert;
	protected List<TweetTemplate> templates;
	protected List<Poison> poisons;
	
	public AngelOfDeath(){}

	private AngelOfDeath(List<String> dataToInsert) {
		this.dataToInsert = dataToInsert;
	}

	private AngelOfDeath(List<String> dataToInsert, List<TweetTemplate> templates, List<Poison> poisons) {
		this.dataToInsert = dataToInsert;
		this.templates = templates;
		this.poisons = poisons;
	}

	public AngelOfDeath poisonTemplatesUsing(List<String> dataToInsert){
		return new AngelOfDeath(dataToInsert);
	}
	
	public AngelOfDeath poisonTemplatesUsing(String listLocation) throws IOException{
		File file = new File(listLocation);
		this.dataToInsert = FileUtils.readLines(file, "UTF-8");
		return this;		
	}
	
	public AngelOfDeath usingTheFollowingPoisons(List<Poison> poisons){
		this.poisons = poisons;
		return this;
	}
	
	public AngelOfDeath templates(List<TweetTemplate> templates){
		this.templates = templates;
		return this;
	}
	
	public AngelOfDeath templates(String directoryLocation) throws IOException{
		
		File file = new File(directoryLocation);
		List<TweetTemplate> tweetTemplates = new ArrayList<>();
		List<String> wordList = FileUtils.readLines(file, "UTF-8");
	
		for (String string : wordList) {
			TweetTemplate template = new TweetTemplate();
			template.setBody(string);
			tweetTemplates.add(template);
		}	
		return this;
	}
	
	private int templateSize(){
		return templates.size();
	}
	
	public int randomTemplateIndex(){
		return new Random(templateSize()).nextInt();
	}
	
	public TweetTemplate getRandomTemplate(){
		return templates.get(randomTemplateIndex());
	}
	
	@Loggable(Loggable.INFO)
	public void poison(){
		try {
			Injector.inject(new AngelOfDeath(dataToInsert, templates, poisons));
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public List<String> getDataToInsert() {
		return dataToInsert;
	}

	public List<TweetTemplate> getTemplates() {
		return templates;
	}

	public List<Poison> getPoisons() {
		return poisons;
	}
	
}
