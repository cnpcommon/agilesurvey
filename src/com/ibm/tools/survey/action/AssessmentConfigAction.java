package com.ibm.tools.survey.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		String questionId = getSafeString(request.getParameter("questionId"));
		String indicatorText = request.getParameter("maturityIndicator"); 
		MaturityIndicator indicator = new MaturityIndicator(principle, practice, level, indicatorText, "Web added ..Input to be taken", 0,questionId);
		if((new SurveyConfigDAO()).updateMaturityIndicator(indicator))
		{
			Gson serializer = new GsonBuilder().create();
			//if save succeeds then update the session object
			Map<String,MaturityIndicator> indicatorMap =(Map<String,MaturityIndicator>) request.getSession().getAttribute(INDICATORS_SESSION_KEY);
			indicatorMap.put(indicator.getQuestionid(),indicator);
			mvObject.setView("{ \"status\": \"0\" , \"payload\" : "+serializer.toJson(indicator)+"}");
		}
		else
		{
			mvObject.setView("{ \"status\": \"1\" }");
		}

		return mvObject;
	}
	@RequestMapping("getQuestionDetails.wss")
	public ModelAndView getQuestionDetails(HttpServletRequest request,
			HttpServletResponse response) {
		MaturityIndicator indicator = null;
		ModelAndView mvObject = new ModelAndView(ViewType.AJAX_VIEW);
		String practice = request.getParameter("practice");
		String principle = request.getParameter("principle");
		int level = Integer.parseInt(request.getParameter("maturityLevel"));
		String questionId = getSafeString(request.getParameter("questionId"));
		//Map<String,List<>>
		Gson serializer = new GsonBuilder().create();
		if(questionId.length()>0)
		{
			indicator = getExistingIndicator(questionId, request);
		}
		else
		{
			indicator = getExistingIndicator(principle,practice,level, request);
		}
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
	private MaturityIndicator getExistingIndicator(String principle,String practice,int level, HttpServletRequest request)
	{
		Map<String,MaturityIndicator> existingDataMap  =(Map<String,MaturityIndicator>) request.getSession().getAttribute(INDICATORS_SESSION_KEY);
		if(existingDataMap==null)
		{
			existingDataMap = loadIndicators();
			request.getSession().setAttribute(INDICATORS_SESSION_KEY,existingDataMap);
		}
		for(MaturityIndicator indicator: existingDataMap.values())
		{
			if(indicator.isMatching(principle, practice, level))
			{
				return indicator;
			}
		}
		return null;
	}
	private MaturityIndicator getExistingIndicator(String questionId,HttpServletRequest request)
	{
		Map<String,MaturityIndicator> existingDataMap  =(Map<String,MaturityIndicator>) request.getSession().getAttribute(INDICATORS_SESSION_KEY);
		if(existingDataMap==null)
		{
			existingDataMap = loadIndicators();
			request.getSession().setAttribute(INDICATORS_SESSION_KEY,existingDataMap);
		}
		
		return existingDataMap.get(questionId);
		
	}
	private Map<String,MaturityIndicator> loadIndicators()
	{
		Map<String,MaturityIndicator> existingDataMap  = new LinkedHashMap<>();
		List<MaturityIndicator> existingIndicators = (new SurveyConfigDAO()).getAllTypes(MaturityIndicator.TYPE, MaturityIndicator.class);
		if(existingIndicators!=null )
		{
			for(MaturityIndicator indicator: existingIndicators)
			{
				existingDataMap.put(indicator.getQuestionid(),indicator);
			}
		}
		return existingDataMap;
				
	}
	@RequestMapping("loadAssesmentConfig.wss")
	public ModelAndView loadConfig(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mvObject = new ModelAndView(ViewType.JSP_VIEW);
		//Gson serializer = new GsonBuilder().create();
		
		// TODO: Change the above item
		mvObject.addModel("principleMap", CachedReferenceDataStore.getAgilePrinciplesMap());
		mvObject.addModel("practiceMap",CachedReferenceDataStore.getAgilePracticeMap());
		mvObject.addModel("levelMap", CachedReferenceDataStore.getLevelsMap());
		mvObject.addModel("indicatorList", (new SurveyConfigDAO().getAllTypes(MaturityIndicator.TYPE, MaturityIndicator.class)));
		
		mvObject.setView("app/surveyconfig.jsp");
		return mvObject;
	}

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
	
	private String getSafeString(String input)
	{
		return (input !=null ? input.trim(): "");
	}

}
