package com.ibm.tools.survey.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.ibm.tools.survey.bean.AssessmentResult;
import com.ibm.tools.survey.bean.Scores;
import com.ibm.tools.survey.dbaccess.ResultScoreDAO;

public class ResultScoreDAOTest {

	private ResultScoreDAO daoObject ;
	@Before
	public void setUp() throws Exception {
		daoObject = new ResultScoreDAO();
	}

	@Test
	public void testInsertScoreData() {
		System.out.println("Inserting test scroe data");
		//<Scores> scoresList= buildScores();
		//assertEquals(true, daoObject.saveData(scoresList));
				
	}
	@Test
	public void testGetResultSquadWise() {
		Gson gson=new Gson();
		List<AssessmentResult> rl=daoObject.getResultSquadWise("894567l","esdw");
		for(AssessmentResult ar:rl){
			System.out.println(gson.toJson(ar));
			System.out.println("practice name "+ar.getPracticeName());
		}
		assertNotNull(rl);
		
	}
	@Test
	public void testGetResultAssesmentWise() {
		Gson gson=new Gson();
		List<AssessmentResult> rl=daoObject.getResultAssesmentWise("894567l");
		for(AssessmentResult ar:rl){
			System.out.println(gson.toJson(ar));
			System.out.println("practice name "+ar.getPracticeName());
		}
		assertNotNull(rl);
		
	}
	public List<Scores> buildScores()
	{
		List<Scores> scoreList = new ArrayList<Scores>();
		/*scoreList.add(new Scores("097088", "cnp1", 894567l, 4, "1_1", "1"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 3, "1_2", "1"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 2, "2_1", "2"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 1, "2_2", "2"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 4, "3_1", "3"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 2, "3_2", "3"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 0, "3_3", "3"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 2, "4_1", "4"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 3, "5_1", "5"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 4, "5_2", "5"));
		scoreList.add(new Scores("097088", "cnp1", 894567l, 2, "5_3", "5"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 2, "1_1", "1"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 4, "1_2", "1"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 3, "2_1", "2"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 0, "2_2", "2"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 2, "3_1", "3"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 4, "3_2", "3"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 1, "3_3", "3"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 4, "4_1", "4"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 0, "5_1", "5"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 1, "5_2", "5"));
		scoreList.add(new Scores("008867", "cnp1", 894567l, 3, "5_3", "5"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 4, "1_1", "1"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 2, "1_2", "1"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 1, "2_1", "2"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 3, "2_2", "2"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 1, "3_1", "3"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 2, "3_2", "3"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 4, "3_3", "3"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 3, "4_1", "4"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 2, "5_1", "5"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 2, "5_2", "5"));
		scoreList.add(new Scores("00397U", "cnp1", 894567l, 4, "5_3", "5"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 4, "1_1", "1"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 2, "1_2", "1"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 1, "2_1", "2"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 3, "2_2", "2"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 1, "3_1", "3"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 2, "3_2", "3"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 4, "3_3", "3"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 3, "4_1", "4"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 2, "5_1", "5"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 2, "5_2", "5"));
		scoreList.add(new Scores("566075", "esdw", 894567l, 4, "5_3", "5"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 4, "1_1", "1"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 3, "1_2", "1"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 2, "2_1", "2"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 1, "2_2", "2"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 4, "3_1", "3"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 2, "3_2", "3"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 0, "3_3", "3"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 2, "4_1", "4"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 3, "5_1", "5"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 4, "5_2", "5"));
		scoreList.add(new Scores("0970SM", "esdw", 894567l, 2, "5_3", "5"));*/
		return scoreList;
	}
}
