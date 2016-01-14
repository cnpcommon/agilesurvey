package com.ibm.tools.survey.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ibm.tools.survey.bean.AgilePractice;
import com.ibm.tools.survey.bean.AgilePrinciple;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.bean.MaturityIndicator;
import com.ibm.tools.survey.bean.MaturityLevel;
import com.ibm.tools.survey.bean.Scores;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class SurveyConfigDAOTest {

	private SurveyConfigDAO daoObject ;
	@Before
	public void setUp() throws Exception {
		daoObject = new SurveyConfigDAO();
	}

	@Test
	public void testGetAssessmentDetails()
	{
		AssesmentDetails assesmentDetails = daoObject.getAssessmentDetails("rajupara@in.ibm.com");
		assertNotNull(assesmentDetails);
	}
	//@Test
	public void testAdhoc()
	{
		daoObject.deleteDataAllType(MaturityIndicator.TYPE);
		System.out.println(daoObject.getAllTypes(MaturityIndicator.TYPE, MaturityIndicator.class));
	}
	//@Test
	public void testInsertRefData() {
		//Build agile principles;
		//List<AgilePrinciple> principles = buildPrinciples();
		//assertEquals(true, daoObject.saveData(principles));
		//List<AgilePractice> practiceList =buildPractices();
		//assertEquals(true, daoObject.saveData(practiceList));
		
		//List<MaturityLevel> maturityLevels =buildMaturityLevel();
		//assertEquals(true, daoObject.saveData(maturityLevels));
	
				
	}
	//@Test
	public void testGetMaturityLevels() {
		//Retrieve agile practices
		List<MaturityLevel> maturityLevels = daoObject.getAllTypes(MaturityLevel.TYPE, MaturityLevel.class);
		assertNotNull(maturityLevels);
		
	}
	//@Test
	public void testGetAgilePractices() {
		//Retrieve agile practices
		List<AgilePractice> principles = daoObject.getAllTypes(AgilePractice.TYPE, AgilePractice.class);
		assertNotNull(principles);
		
	}
	//@Test
	public void testGetAgilePrinciples() {
		//Build agile principles;
		List<AgilePrinciple> principles = daoObject.getAllTypes(AgilePrinciple.TYPE, AgilePrinciple.class);
		assertNotNull(principles);
		
	}
	private List<AgilePractice> buildPractices()
	{
		List<AgilePractice> pacticeList = new ArrayList<>();
		pacticeList.add(new AgilePractice("1", "1_1", "Standups", "A regular team collaboration opportunity for sharing progress, identifying risks and resolving issues", 1, "Locaed maunally "));
		pacticeList.add(new AgilePractice("1", "1_2", "Track & Visualize Progress (Walls)", "Tracking progress of work and visualizing to create transparency and shared understanding ", 2, "Locaed maunally "));
		pacticeList.add(new AgilePractice("2", "2_1", "Engaging the Product Owner", "through showcases, planning, progress and team meetings to support definition and prioritization", 1, "Locaed maunally "));
		pacticeList.add(new AgilePractice("2", "2_2", "Work Prioritization", "Effective work prioritization ensures that the greatest business value is always being delivered thereby maximizing return on investment.", 2, "Locaed maunally "));
		pacticeList.add(new AgilePractice("3", "3_1", "Adaptive Planning (Release and Iteration Planning)", "The collaborative identification, definition and planning of work and deliverables", 1, "Locaed maunally "));
		pacticeList.add(new AgilePractice("3", "3_2", "Retrospectives", "A team activity used for problem identification and solution forming the basis for continuous improvement", 2, "Locaed maunally "));
		pacticeList.add(new AgilePractice("3", "3_3", "Work Estimation (Relative Estimates)", "Relative estimations of work (both effort and cost) are essential for planning", 3, "Locaed maunally "));
		pacticeList.add(new AgilePractice("4", "4_1", "Work Break Down (Stories)", "Features and Stories capture and communicate the work that is needed in small well defined pieces", 1, "Locaed maunally "));
		pacticeList.add(new AgilePractice("5", "5_1", "Stable Cross-Functional Teams", "Cross-functional teams have all competencies needed to accomplish the work without depending on others not part of the team. The team is designed to optimize flexibility, creativity, and productivity", 1, "Locaed maunally "));
		pacticeList.add(new AgilePractice("5", "5_2", "Social Contract", "A living document, with the mutual expectations and agreements of the team and is prominently posted in the team area (if the team is co-located) or in a wiki, which is easily accessible", 2, "Locaed maunally "));
		pacticeList.add(new AgilePractice("5", "5_3", "Risk and Issue Management", "Risk and Issue management addresses uncertainty and increases the likelihood of successful outcomes", 3, "Locaed maunally "));
		
		
		return pacticeList;
	}
	
	private List<AgilePrinciple> buildPrinciples()
	{
		List<AgilePrinciple> principles = new ArrayList<>();
		principles.add(new AgilePrinciple("1","Collaboration and Teamwork",1,"Loaded manually"));
		principles.add(new AgilePrinciple("2","Focus on the Customer and Business Value",2,"Loaded manually"));
		principles.add(new AgilePrinciple("3","Flexible, Adaptive and Continuously Improving",3,"Loaded manually"));
		principles.add(new AgilePrinciple("4","Iterative and Fast",4,"Loaded manually"));
		principles.add(new AgilePrinciple("5","Empowered and Self Directed Teams",5,"Loaded manually"));
		return principles;
	}

	private List<MaturityLevel> buildMaturityLevel()
	{
		List<MaturityLevel> levels = new ArrayList<>();
		levels.add(new MaturityLevel(0, "Don't know", "Manually loaded", 1));
		levels.add(new MaturityLevel(1, "Initiating", "Manually loaded", 2));
		levels.add(new MaturityLevel(2, "Practicing", "Manually loaded", 3));
		levels.add(new MaturityLevel(3, "Transforming", "Manually loaded", 4));
		levels.add(new MaturityLevel(4, "Scaled", "Manually loaded", 5));
		levels.add(new MaturityLevel(-1, "Not applicable", "Manuallyy loaded", 6));
		
		return levels;
	}
	public List<Scores> buildScores()
	{
		List<Scores> scoreList = new ArrayList<Scores>();
		scoreList.add(new Scores("097088", "cnp1", 12345l, 4, "1-1", "1"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 3, "1-2", "1"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 2, "1-1", "2"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 1, "2-2", "2"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 4, "3-1", "3"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 2, "3-2", "3"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 0, "3-3", "3"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 2, "4-1", "4"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 3, "5-1", "5"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 4, "5-2", "5"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 2, "5-3", "5"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 2, "1-1", "1"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 4, "1-2", "1"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 3, "1-1", "2"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 0, "2-2", "2"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 2, "3-1", "3"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 4, "3-2", "3"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 1, "3-3", "3"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 4, "4-1", "4"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 0, "5-1", "5"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 1, "5-2", "5"));
		scoreList.add(new Scores("008867", "cnp1", 12345l, 3, "5-3", "5"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 4, "1-1", "1"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 2, "1-2", "1"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 1, "1-1", "2"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 3, "2-2", "2"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 1, "3-1", "3"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 2, "3-2", "3"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 4, "3-3", "3"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 3, "4-1", "4"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 2, "5-1", "5"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 2, "5-2", "5"));
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 4, "5-3", "5"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 4, "1-1", "1"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 2, "1-2", "1"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 1, "1-1", "2"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 3, "2-2", "2"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 1, "3-1", "3"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 2, "3-2", "3"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 4, "3-3", "3"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 3, "4-1", "4"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 2, "5-1", "5"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 2, "5-2", "5"));
		scoreList.add(new Scores("566075", "esdw", 12345l, 4, "5-3", "5"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 4, "1-1", "1"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 3, "1-2", "1"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 2, "1-1", "2"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 1, "2-2", "2"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 4, "3-1", "3"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 2, "3-2", "3"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 0, "3-3", "3"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 2, "4-1", "4"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 3, "5-1", "5"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 4, "5-2", "5"));
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 2, "5-3", "5"));
		return scoreList;
	}
}
