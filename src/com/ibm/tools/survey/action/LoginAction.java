package com.ibm.tools.survey.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class LoginAction implements WebActionHandler {
	Gson gson = new Gson();
	@RequestMapping("login.wss")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		
		 MongoCollection<Document> collection =MongoDBHelper.getCollection();
		 BasicDBObject whereQuery = new BasicDBObject();
   	     whereQuery.put("emailId", email);
   	      
   	    Document cursorDoc = collection.find(whereQuery).first();
   	 
		
		UserDetails userDetails  = gson.fromJson(cursorDoc.toJson(),UserDetails.class );
		
		if(password!=null && password.equals(userDetails.getPassword())){
		userDetails.setRole(ApplicationConstants.USER_ROLE_ITERATION_MGR);
		request.getSession().setAttribute("LOGGED_IN_USER", userDetails);
		mvObject.setView("app/home.jsp");
		}else{
			mvObject.addModel("loginError", "Invalid credentials");
			mvObject.setView("/index.jsp");
		}
		return mvObject;
	}
}
