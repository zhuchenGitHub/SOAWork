package Assignment2;

public class Score {
	private int normalScore;
	private int testScore;
	private int finalScore;
	DOM dom=new DOM();
	public Score(int normalScore, int testScore) {
		this.normalScore = normalScore;
		this.testScore = testScore;
		finalScore=normalScore+testScore;
	}
	
	public int getNormal(){
		return normalScore;
	}
	public int getTest(){
		return testScore;
	}
	public int getFinal(){
		return finalScore;
	}
}
