package com.ibm.tools.survey.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.UserDetails;

public class LoginAction implements WebActionHandler {

	@RequestMapping("login.wss")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		//TODO: to read from DB and validate
		UserDetails userDetails  = new UserDetails();
		userDetails.setRole(ApplicationConstants.USER_ROLE_ITERATION_MGR);
		request.getSession().setAttribute("LOGGED_IN_USER", userDetails);
		//TODO: Change the above item
		mvObject.setView("app/home.jsp");
		return mvObject;
	}
}
