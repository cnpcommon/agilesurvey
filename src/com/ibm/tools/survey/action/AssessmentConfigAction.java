package com.ibm.tools.survey.action;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.CachedReferenceDataStore;

public class AssessmentConfigAction implements WebActionHandler {

	
	@RequestMapping("loadExistingQuestionConfig.wss")
	public ModelAndView loadExistingQuestionConfig(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);

		// TODO: Change the above item
		mvObject.setView("app/questionConfig.jsp");
		return mvObject;
	}
	
	
	@RequestMapping("loadAssesmentConfig.wss")
	public ModelAndView loadConfig(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);

		// TODO: Change the above item
		mvObject.setView("app/surveyconfig.jsp");
		return mvObject;
	}

	@RequestMapping("displayQuestionSetup.wss")
	public ModelAndView laodQuestionSetup(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		Gson serializer = new GsonBuilder().create();
		AssesmentDetails assesmentDetails = buildNewAssesment(request);
		// TODO : Validation
		request.getSession().setAttribute("__NEW_ASSESMENT", assesmentDetails);
		mvObject.addModel("assesmentDetails", assesmentDetails);
		mvObject.addModel("principleList", CachedReferenceDataStore.getPrinciples());
		mvObject.addModel("practiceMap",serializer.toJson(CachedReferenceDataStore.getPrincipleToPracticeMap()));
		mvObject.addModel("levelList", CachedReferenceDataStore.getLevels());
		mvObject.setView("app/questionsetup.jsp");
		return mvObject;
	}

	@RequestMapping("displayQuestionSetup.wss")
	
	private AssesmentDetails buildNewAssesment(HttpServletRequest request) {
		String surveyName = request.getParameter("surveyName");
		String releaseDate = request.getParameter("releaseDate");
		String[] squadList = request.getParameterValues("sqadList");
		String comment = request.getParameter("comment");
		String ownerId = ((UserDetails) request.getSession().getAttribute(
				"LOGGED_IN_USER")).getEmailId();
		AssesmentDetails assesmentDetauls = new AssesmentDetails(
				(new Date()).getTime(), ownerId, Arrays.asList(squadList),
				surveyName, releaseDate, comment);
		return assesmentDetauls;
	}

}
