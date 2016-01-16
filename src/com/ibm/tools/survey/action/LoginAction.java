package com.ibm.tools.survey.action;

import static com.ibm.tools.survey.bean.ApplicationConstants.LOGGED_IN_USER;
import static com.ibm.tools.survey.bean.ApplicationConstants.USER_ROLE_ITERATION_MGR;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.client.MongoCollection;

public class LoginAction implements WebActionHandler {
	private Gson gson = new Gson();
	
	@RequestMapping("login.wss")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		MongoCollection<Document> collection = MongoDBHelper.getCollection();
		Document cursorDoc = collection.find(
				and(eq("type", UserDetails.TYPE), eq("emailId", email))).first();
		if (cursorDoc != null) {
			UserDetails userDetails = gson.fromJson(cursorDoc.toJson(), UserDetails.class);
			if (password != null && password.equals(userDetails.getPassword())) {
				request.getSession()
						.setAttribute(LOGGED_IN_USER, userDetails);
				mvObject = new ModelAndView(ViewType.FORWARD_ACTION_VIEW);
				if(USER_ROLE_ITERATION_MGR.equalsIgnoreCase(userDetails.getRole().trim()))
				{
					mvObject.setView("loadItrMgrHomeDashboard.wss");
				}
				else
				{
					mvObject.setView("home.wss");
				}
			} else {
				mvObject.addModel("loginError", "Invalid credentials");
				mvObject.setView("/index.jsp");
			}
		} else {
			mvObject.addModel("loginError", "Invalid credentials");
			mvObject.setView("/index.jsp");
		}
		return mvObject;
	}
}
