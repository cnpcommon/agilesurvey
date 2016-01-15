package com.ibm.tools.survey.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.AssessmentResult;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.ResultScoreDAO;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class HomeAction implements WebActionHandler {

	@RequestMapping("home.wss")
	public ModelAndView loadHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		mvObject.setView("app/newhome.jsp");
		return mvObject;
	}

	@RequestMapping("gendashboard.wss")
	public ModelAndView loadDashboard(HttpServletRequest request, HttpServletResponse response) {
		UserDetails usrDetails = (UserDetails) request.getSession()
				.getAttribute("LOGGED_IN_USER");
		List<AssesmentDetails> filteredAssesmentDetails = new SurveyConfigDAO()
				.getAssesmentsByOwner(usrDetails.getEmailId());
        ModelAndView mvObject=new ModelAndView(ViewType.JSP_VIEW);
        mvObject.addModel("assesmentDetails", filteredAssesmentDetails);
        mvObject.setView("app/dashboard.jsp");
        return mvObject;
		
	}
	
	@RequestMapping("getmaturityscore.wss")
	public ModelAndView showMaturityScore(HttpServletRequest request, HttpServletResponse response){
		UserDetails usrDetails = (UserDetails) request.getSession().getAttribute("LOGGED_IN_USER");
		String assesmentId=request.getParameter("assesmentId");
		ModelAndView mvObject=new ModelAndView(ViewType.NO_VIEW);
		List<AssessmentResult> listOfResults=new ResultScoreDAO().getResultAssesmentWise(assesmentId);
		AjaxDataWriter<List<AssessmentResult>> writer= new AjaxDataWriter<>();
		writer.write(response, listOfResults, (Class<List<AssessmentResult>>) listOfResults.getClass());
		return mvObject;
	}
}
