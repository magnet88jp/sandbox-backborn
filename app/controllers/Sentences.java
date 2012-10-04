package controllers;
 
import java.util.*;
import play.*;
import play.mvc.*;

import models.*;
 
@With(Secure.class)
public class Sentences extends CRUD {

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
		/*
		Map<String, String[]> all = params.all();
//        for(Map<String, String[]> param : all) {
//    System.out.println(e.getKey() + " : " + e.getValue());
//  		  Logger.info("debug:3:"+param);
//        }
        Iterator it = all.keySet().iterator();
        while (it.hasNext()) {
          Object o = it.next();
//            System.out.println(o + " = " + map.get(o));
  		  Logger.info("debug:3:"+o+"="+all.get(o).toString());
        }
        */
  		Logger.info("debug:4:id:"+params.get("id"));
  		Logger.info("debug:5:body:"+params.get("body"));


	}

}
