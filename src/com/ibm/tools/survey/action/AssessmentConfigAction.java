package com.ibm.tools.survey.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.app.web.frmwk.WebActionHandler;
import com.ibm.app.web.frmwk.annotations.RequestMapping;
import com.ibm.app.web.frmwk.bean.ModelAndView;
import com.ibm.app.web.frmwk.bean.ViewType;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.MaturityIndicator;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.CachedReferenceDataStore;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class AssessmentConfigAction implements WebActionHandler {

	private static final String INDICATORS_SESSION_KEY ="INDICATORS";
	@RequestMapping("loadExistingQuestionConfig.wss")
	public ModelAndView loadExistingQuestionConfig(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		Gson serializer = new GsonBuilder().create();
		mvObject.addModel("principleList", CachedReferenceDataStore.getPrinciples());
		mvObject.addModel("practiceMap",serializer.toJson(CachedReferenceDataStore.getPrincipleToPracticeMap()));
		mvObject.addModel("levelList", CachedReferenceDataStore.getLevels());
		mvObject.setView("app/questionconfig.jsp");
		return mvObject;
	}
	@RequestMapping("saveQuestionDetails.wss")
	public ModelAndView saveQuestionDetails(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		//Build the indicator
		String practice = request.getParameter("practice");
		String principle = request.getParameter("principle");
		int level = Integer.parseInt(request.getParameter("maturityLevel"));
		String indicatorText = request.getParameter("maturityIndicator");
		MaturityIndicator indicator = new MaturityIndicator(principle, practice, level, indicatorText, "Web added ..Input to be taken", 0);
		if((new SurveyConfigDAO()).updateMaturityIndicator(indicator))
		{
			//if save succeeds then update the session object
			List<MaturityIndicator> existingIndicators =(List<MaturityIndicator>) request.getSession().getAttribute(INDICATORS_SESSION_KEY);
			existingIndicators.remove(indicator);
			existingIndicators.add(indicator);
			mvObject.setView("{ \"status\": 0 }");
		}
		else
		{
			mvObject.setView("{ \"status\": 1 }");
		}

		return mvObject;
	}
	@RequestMapping("getQuestionDetails.wss")
	public ModelAndView getQuestionDetails(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String practice = request.getParameter("practice");
		String principle = request.getParameter("principle");
		int level = Integer.parseInt(request.getParameter("maturityLevel"));
		//Map<String,List<>>
		Gson serializer = new GsonBuilder().create();

		MaturityIndicator indicator = getExistingIndicator(principle, practice, level, request);
		if(indicator==null)
		{
			mvObject.setView("{ \"status\": \"1\" }");
		}
		else
		{
			mvObject.setView("{ \"status\" :  \"0\" , \"payload\" : "+serializer.toJson(indicator)+"}");
		}
		return mvObject;
	}
	
	private MaturityIndicator getExistingIndicator(String principle,String practice,int level,HttpServletRequest request)
	{
		List<MaturityIndicator> existingIndicators =(List<MaturityIndicator>) request.getSession().getAttribute(INDICATORS_SESSION_KEY);
		if(existingIndicators==null)
		{
			existingIndicators = loadIndicators();
			request.getSession().setAttribute(INDICATORS_SESSION_KEY,existingIndicators);
		}
		int index= existingIndicators.indexOf(new MaturityIndicator(principle,practice,level));
		return (index==-1? null: existingIndicators.get(index));
		
	}
	private List<MaturityIndicator> loadIndicators()
	{
		List<MaturityIndicator> existingIndicators = (new SurveyConfigDAO()).getAllTypes(MaturityIndicator.TYPE, MaturityIndicator.class);
		if(existingIndicators==null )
		{
			existingIndicators = new ArrayList<>();
		}
		return existingIndicators;
				
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
