package com.ibm.tools.survey.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.security.action.GetLongAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.AssessmentResult;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.ResultScoreDAO;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class HomeAction implements WebActionHandler {

	@RequestMapping("home.wss")
	public ModelAndView loadHome(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		UserDetails userDetails = getLoggedInUser(request);
		if(userDetails!=null && ApplicationConstants.USER_ROLE_ITERATION_MGR.equalsIgnoreCase(userDetails.getRole()))
		{
			mvObject = new ModelAndView(ViewType.FORWARD_ACTION_VIEW);
			mvObject.setView("loadItrMgrHomeDashboard.wss");
		}
		else if(userDetails!=null && ApplicationConstants.USER_ROLE_TRIBE_MANAGER.equalsIgnoreCase(userDetails.getRole()))
		{
			mvObject = new ModelAndView(ViewType.FORWARD_ACTION_VIEW);
			mvObject.setView("gendashboard.wss");
		}
		else
		{
			mvObject.setView("app/newhome.jsp");
		}
		return mvObject;
	}

	@RequestMapping("gendashboard.wss")
	public ModelAndView loadDashboard(HttpServletRequest request,
			HttpServletResponse response) {
		UserDetails usrDetails = getLoggedInUser(request);
		List<AssesmentDetails> filteredAssesmentDetails = new SurveyConfigDAO()
				.getAssesmentsByOwner(usrDetails.getEmailId());

		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.addModel("assesmentDetails", filteredAssesmentDetails);
		mvObject.setView("app/dashboard.jsp");
		return mvObject;

	}

	@RequestMapping("getmaturityscore.wss")
	public ModelAndView showMaturityScore(HttpServletRequest request,
			HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		String assesmentId = request.getParameter("assesmentId");
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		List<AssessmentResult> listOfResults = new ResultScoreDAO()
				.getResultAssesmentWise(assesmentId);
		mvObject.setView(gson.toJson(listOfResults));
		
		return mvObject;
	}

	@RequestMapping("logout.wss")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		session.removeAttribute("LOGGED_IN_USER");
		session.invalidate();
		mvObject.setView("index.jsp");
		return mvObject;
	}
	
	@RequestMapping("getsquadscore.wss")
	public ModelAndView showMaturityScorePerSquad(HttpServletRequest request,HttpServletResponse response){
		Gson gson = new GsonBuilder().create();
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String assesmentId = request.getParameter("assesmentId");
		String squadId=request.getParameter("squadId");
		List<AssessmentResult> listOfResults=new ResultScoreDAO().getResultSquadWise(assesmentId, squadId);
		mvObject.setView(gson.toJson(listOfResults));
		return mvObject;
	}
	
	private UserDetails getLoggedInUser(HttpServletRequest request)
	{
		UserDetails usrDetails = (UserDetails) request.getSession()
				.getAttribute(ApplicationConstants.LOGGED_IN_USER);
		return usrDetails;
	}
}
