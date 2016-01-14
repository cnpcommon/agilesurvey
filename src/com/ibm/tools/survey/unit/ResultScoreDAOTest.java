package com.ibm.tools.survey.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
		//List<Scores> scoresList= buildScores();
		//assertEquals(true, daoObject.saveData(scoresList));
				
	}
	@Test
	public void testGetResults() {
		//daoObject.getResults(12345l,"esdw");
		assertEquals(true, daoObject.getResults(12345l,"esdw"));
		
	}
	public List<Scores> buildScores()
	{
		List<Scores> scoreList = new ArrayList<Scores>();
		scoreList.add(new Scores("097088", "cnp1", 12345l, 4, "1-1", "1"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 3, "1-2", "1"));
		scoreList.add(new Scores("097088", "cnp1", 12345l, 2, "2-1", "2"));
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
		scoreList.add(new Scores("008867", "cnp1", 12345l, 3, "2-1", "2"));
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
		scoreList.add(new Scores("00397U", "cnp1", 12345l, 1, "2-1", "2"));
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
		scoreList.add(new Scores("566075", "esdw", 12345l, 1, "2-1", "2"));
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
		scoreList.add(new Scores("0970SM", "esdw", 12345l, 2, "2-1", "2"));
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
