package controllers;
 
import java.util.*;
import play.*;
import play.mvc.*;

import com.google.gson.Gson;

import models.*;
 
@With(Secure.class)
public class Sentences extends CRUD {

    private class SentenceData {
        public Long id;
        public String name;
        public String email;

        public Long getId(){
        	return id;
        }
        public String getName(){
        	return name;
        }
        public String getEmail(){
        	return email;
        }
    }

	public static void json() {
		List<Sentence> sentences = Sentence.findAll();
//        Logger.info("debug:"+sentences.toString());
		renderJSON(sentences);
	}

	@After(only={"save"})
	static void setRenderArgs(Long id) {
		String name = params.get("name");
//		Logger.info("debug:2:"+id.toString());
		Logger.info("debug:3:"+name);

	}

	public static void jsonSave() {
//  		Logger.info("debug:4:id:"+params.get("id"));
//  		Logger.info("debug:5:body:"+params.get("body"));

  		Gson gson = new Gson();
  		SentenceData sentenceData = gson.fromJson(params.get("body"), SentenceData.class);

//  		Logger.info("name=" + sentenceData.getName());
//  		Logger.info("email=" + sentenceData.getEmail());

//        Map<String, String[]> all = params.all();
//        Iterator it = all.keySet().iterator();
//        while (it.hasNext()) {
//          Object o = it.next();
//          Logger.info("debug:3:"+o+"="+all.get(o).toString());
//        }

  		Sentence sentence = Sentence.findById(sentenceData.getId());
  		sentence.name = sentenceData.getName();
  		sentence.email = sentenceData.getEmail();
  		sentence.save();

	}

	public static void jsonCreate() {
  		Gson gson = new Gson();
  		SentenceData sentenceData = gson.fromJson(params.get("body"), SentenceData.class);
  		Logger.info("debug:body=" + params.get("body"));

  		Sentence sentence = new Sentence(sentenceData.getName(), sentenceData.getEmail());
  		sentence.save();
	}

}
