package controllers;
 
import play.*;
import play.mvc.*;

import models.*;
 
@With(Secure.class)
@CRUD.For(Entry.class)
public class Note extends CRUD {

	@Before(only={"list"})
	static void setRenderArgs(Long id) {

	}
}