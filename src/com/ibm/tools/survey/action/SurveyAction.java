package com.ibm.tools.survey.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.CachedReferenceDataStore;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class SurveyAction implements WebActionHandler{

	@RequestMapping("loadSurvey.wss")
	public ModelAndView loadSurvey(HttpServletRequest request,HttpServletResponse response)
	{
		boolean isValid = false;
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		String userId = getLoggedInUser(request);
		if(userId!=null)
		{
			AssesmentDetails assignedAssesment = (new SurveyConfigDAO()).getAssessmentDetails(userId);
			if(assignedAssesment!=null)
			{
				mvObject.setView("app/survey.jsp");
				mvObject.addModel("assesment", assignedAssesment);
				mvObject.addModel("principleMap",
						CachedReferenceDataStore.getAgilePrinciplesMap());
				mvObject.addModel("practiceMap",
						CachedReferenceDataStore.getAgilePracticeMap());
				mvObject.addModel("levels", CachedReferenceDataStore.getLevels());
				isValid = true;
			}
			
		}
		if(!isValid)
		{
			mvObject = new ModelAndView(ViewType.FORWARD_ACTION_VIEW);
			mvObject.setView("home.wss");
		}
		
		return mvObject;
	}
	private String getLoggedInUser(HttpServletRequest request)
	{
		UserDetails userDetails = (UserDetails)request.getSession().getAttribute("LOGGED_IN_USER");
		if(userDetails!=null)
		{
			return userDetails.getEmailId();
		}
		return null;
	}
}
