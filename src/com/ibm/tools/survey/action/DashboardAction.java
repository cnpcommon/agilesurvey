package com.ibm.tools.survey.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.MaturityAssesmentUser;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;


public class DashboardAction implements WebActionHandler {

	@RequestMapping("loadItrMgrHomeDashboard.wss")
	public ModelAndView loadInterationManagerDashboard(
			HttpServletRequest request, HttpServletResponse response) {

		SurveyConfigDAO daoObject = new SurveyConfigDAO();
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		UserDetails usrDetails = getLoggedInUserDetails(request);
		mvObject.setView("app/newhome.jsp");
		if (usrDetails != null
				&& ApplicationConstants.USER_ROLE_ITERATION_MGR
						.equalsIgnoreCase(usrDetails.getRole().trim())) {
			// Get the squad details
			MaturityAssesmentUser hieracrchyDetails = daoObject
					.getMatutiryAssessmentInfoForUser(usrDetails.getEmailId());
			if (hieracrchyDetails != null
					&& hieracrchyDetails.getSquadId() != null) {
				List<AssesmentDetails> assessmentList = daoObject.getAssesmentsForSquad(hieracrchyDetails.getSquadId());
				mvObject.addModel("assessmentList", assessmentList);
				mvObject.addModel("squadDetails",hieracrchyDetails.getSquadId());
				mvObject.setView("app/itrmgrhome.jsp");
			}
		}
		return mvObject;
	}

	private UserDetails getLoggedInUserDetails(HttpServletRequest request) {

		return (UserDetails) request.getSession()
				.getAttribute("LOGGED_IN_USER");

	}
}
