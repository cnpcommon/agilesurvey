package com.ibm.tools.survey.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.UserDetails;

public class AssessmentConfigAction implements WebActionHandler {

	
	@RequestMapping("loadAssesmentConfig.wss")
	public ModelAndView loadConfig(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		
		//TODO: Change the above item
		mvObject.setView("app/surveyconfig.jsp");
		return mvObject;
	}
	
	@RequestMapping("displayQuestionSetup.wss")
	public ModelAndView laodQuestionSetup(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		
		
		mvObject.setView("app/questiosetup.jsp");
		return mvObject;
	}
}
