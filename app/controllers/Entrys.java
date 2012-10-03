package controllers;
 
import java.util.*;
import play.*;
import play.mvc.*;

import models.*;
 
@With(Secure.class)
public class Entrys extends CRUD {

	public static void json() {
		List<Entry> entrys = Entry.findAll();
                Logger.info("debug:"+entrys.toString());
		renderJSON(entrys);
	}
}
