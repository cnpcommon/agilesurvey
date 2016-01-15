package com.ibm.tools.survey.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.AssessmentResult;
import com.ibm.tools.survey.bean.MaturityAssesmentUser;
import com.ibm.tools.survey.bean.MaturityIndicatorInfoMap;
import com.ibm.tools.survey.bean.Scores;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.CachedReferenceDataStore;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class SurveyAction implements WebActionHandler {

	private final String ASSESMENT_KEY = "ASSESMENT_KEY";

	@RequestMapping("loadSurvey.wss")
	public ModelAndView loadSurvey(HttpServletRequest request,
			HttpServletResponse response) {
		boolean isValid = false;
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		String userId = getLoggedInUser(request);
		if (userId != null) {
			AssesmentDetails assignedAssesment = (new SurveyConfigDAO())
					.getAssessmentDetails(userId);
			if (assignedAssesment != null) {
				mvObject.setView("app/survey.jsp");
				mvObject.addModel("assesment", assignedAssesment);
				request.getSession().setAttribute(ASSESMENT_KEY,
						assignedAssesment);
				mvObject.addModel("principleMap",
						CachedReferenceDataStore.getAgilePrinciplesMap());
				mvObject.addModel("practiceMap",
						CachedReferenceDataStore.getAgilePracticeMap());
				mvObject.addModel("levels",
						CachedReferenceDataStore.getLevels());
				isValid = true;
			}

		}
		if (!isValid) {
			mvObject = new ModelAndView(ViewType.FORWARD_ACTION_VIEW);
			mvObject.setView("home.wss");
		}

		return mvObject;
	}

	@RequestMapping("saveSurveyResults.wss")
	public ModelAndView saveSurveyResults(HttpServletRequest request,
			HttpServletResponse response) {
		SurveyConfigDAO daoObject = new SurveyConfigDAO();
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		AssesmentDetails currentAssesment = (AssesmentDetails) request
				.getSession().getAttribute(ASSESMENT_KEY);
		if (currentAssesment != null) {
			// TODO: VALIDATION
			
			UserDetails userDetails = getLoggedInUserDetails(request);
			MaturityAssesmentUser userInfo = daoObject
					.getMatutiryAssessmentInfoForUser(userDetails.getEmailId());
			List<Scores> scores = new ArrayList<>();
			for (MaturityIndicatorInfoMap info : currentAssesment
					.getIndicatorMap()) {
				int levelSelected = Integer.parseInt(request.getParameter(info
						.getItemId()));
				Scores rslt = new Scores(userDetails.getEmailId(),
						userInfo.getSquadId(), currentAssesment.getAssessementId(), levelSelected,
						info.getPracticeId(), info.getPrincipleId());
				scores.add(rslt);
			}
			if (scores.size() > 0) {
				if (daoObject.saveScores(scores)) {
					mvObject.setView("{ \"status\" :  \"0\" } ");
				} else {
					mvObject.setView("{ \"status\" :  \"1\" , \"errorMessage\" : "
							+ "Unable to save assessment details" + "}");
				}
			}

		} else {
			mvObject.setView("{ \"status\" :  \"1\" , \"errorMessage\" : "
					+ "Unable to get assingment details" + "}");
		}
		return mvObject;
	}

	private String getLoggedInUser(HttpServletRequest request) {
		UserDetails userDetails = getLoggedInUserDetails(request);
		if (userDetails != null) {
			return userDetails.getEmailId();
		}
		return null;
	}

	private UserDetails getLoggedInUserDetails(HttpServletRequest request) {

		return (UserDetails) request.getSession()
				.getAttribute("LOGGED_IN_USER");

	}
}
