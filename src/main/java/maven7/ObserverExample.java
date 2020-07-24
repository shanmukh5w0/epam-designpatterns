package maven7;

//Java program to demonstrate working of 
//onserver pattern 
import java.util.ArrayList; 
import java.util.Iterator; 

//Implemented by Cricket data to communicate 
//with observers 
interface Subject 
{ 
	public void registerObserver(Observer o); 
	public void unregisterObserver(Observer o); 
	public void notifyObservers(); 
} 

class CricketData implements Subject 
{ 
	int runs; 
	int wickets; 
	float overs; 
	ArrayList<Observer> observerList; 

	public CricketData() { 
		observerList = new ArrayList<Observer>(); 
	} 

	public void registerObserver(Observer o) { 
		observerList.add(o); 
	} 

	public void unregisterObserver(Observer o) { 
		observerList.remove(observerList.indexOf(o)); 
	} 

	
	public void notifyObservers() 
	{ 
		for (Iterator<Observer> it = 
			observerList.iterator(); it.hasNext();) 
		{ 
			Observer o = it.next(); 
			o.update(runs,wickets,overs); 
		} 
	}  
	private int getLatestRuns() 
	{ 

		return 90; 
	} 

	private int getLatestWickets() 
	{ 

		return 2; 
	} 

	private float getLatestOvers() 
	{ 
		return (float)10.2; 
	} 

	public void dataChanged() 
	{ 
		runs = getLatestRuns(); 
		wickets = getLatestWickets(); 
		overs = getLatestOvers(); 

		notifyObservers(); 
	} 
} 
interface Observer 
{ 
	public void update(int runs, int wickets, 
					float overs); 
} 

class AverageScoreDisplay implements Observer 
{ 
	private float runRate; 
	private int predictedScore; 

	public void update(int runs, int wickets, 
					float overs) 
	{ 
		this.runRate =(float)runs/overs; 
		this.predictedScore = (int)(this.runRate * 50); 
		display(); 
	} 

	public void display() 
	{ 
		System.out.println("\nAverage Score Display: \n"+ "Run Rate: " + runRate + "\nPredictedScore: " + predictedScore); 
	} 
} 

class CurrentScoreDisplay implements Observer 
{ 
	private int runs, wickets; 
	private float overs; 

	public void update(int runs, int wickets, 
					float overs) 
	{ 
		this.runs = runs; 
		this.wickets = wickets; 
		this.overs = overs; 
		display(); 
	} 

	public void display() 
	{ 
		System.out.println("\nCurrent Score Display:\n"+ "Runs: " + runs + "\nWickets:" + wickets + "\nOvers: " + overs ); 
	} 
} 

public class ObserverExample {
	public static void main(String args[]) 
	{ 
		AverageScoreDisplay averageScoreDisplay = new AverageScoreDisplay(); 
		CurrentScoreDisplay currentScoreDisplay = new CurrentScoreDisplay(); 
		CricketData cricketData = new CricketData(); 
		cricketData.registerObserver(averageScoreDisplay); 
		cricketData.registerObserver(currentScoreDisplay); 		
		cricketData.dataChanged(); 
		cricketData.unregisterObserver(averageScoreDisplay); 
		cricketData.dataChanged(); 
	} 

}
