package thesis;

public class User 
{
	//These are the membership grades for each of the qualities
	private double energy;		//The higher the energy the more energy the user has
	private double appetite;	//The higher the appetite the more hungry the user is
	private double spareTime;	//The higher the spareTime the more spare time the user has
	
	public User(double e, double app, double spare)
	{
		energy = e;
		appetite = app;
		spareTime = spare;
	}
	
	//Getters
	public double getEnergy()
	{
		return energy;
	}
	
	public double getAppetite()
	{
		return appetite;
	}
	
	public double getSpareTime()
	{
		return spareTime;
	}
}