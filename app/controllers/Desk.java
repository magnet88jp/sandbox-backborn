package controllers;
 
import play.*;
import play.mvc.*;

import models.*;
 
@With(Secure.class)
@CRUD.For(Sentence.class)
public class Desk extends CRUD {

	@Before(only={"list"})
	static void setRenderArgs(Long id) {

	}
}