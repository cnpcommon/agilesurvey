package com.ibm.tools.survey.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.MaturityAssesmentUser;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

public class LoginAction implements WebActionHandler {
	private Gson gson = new Gson();
	private MongoCollection<Document> collection = null;
	private BasicDBObject whereQuery = null;
	private Document cursorDoc;
	private UserDetails userDetails;
	private MaturityAssesmentUser msUser;
	@RequestMapping("login.wss")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		
		 collection =MongoDBHelper.getCollection();
		 
		 
		 /*BasicDBObject andQuery = new BasicDBObject();
		 List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		    obj.add(new BasicDBObject("type", "UserDetails"));
		    obj.add(new BasicDBObject("emailId", email));
		    andQuery.put("$and", obj);*/
   	    cursorDoc = collection.find(and(eq("type","UserDetails"),eq("emailId",email))).first();
   	 
   	    if(cursorDoc!=null){
		
		 userDetails  = gson.fromJson(cursorDoc.toJson(),UserDetails.class );
		
			if(password!=null && password.equals(userDetails.getPassword())){
			userDetails.setRole(ApplicationConstants.USER_ROLE_ITERATION_MGR);
			request.getSession().setAttribute("LOGGED_IN_USER", userDetails);
			//Fllowing code was throwning exception 
			//Hence suddutt1 commented it out
			/*
 			 whereQuery = new BasicDBObject();
	   	     whereQuery.put("id", userDetails.getEmailId());
	   	     cursorDoc = collection.find(whereQuery).first();
	   	     msUser = gson.fromJson(cursorDoc.toJson(),MaturityAssesmentUser.class );
	   	     request.getSession().setAttribute("ITTERATION_MANAGER_INFO", msUser);
			 */			 
			mvObject.setView("app/newhome.jsp");
			}else{
				mvObject.addModel("loginError", "Invalid credentials");
				mvObject.setView("/index.jsp");
			 }
   	    }else{
   	    	mvObject.addModel("loginError", "Invalid credentials");
			mvObject.setView("/index.jsp");
   	    }
		return mvObject;
	}
}
