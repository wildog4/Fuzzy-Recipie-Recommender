package thesis;


//This class holds the variables for the parsed recipes.
public class Recipe 
{
	private double cookingTime;
	private double prepTime;
	private String name;
	private String info;
	private double easyMembership;
	private double medMembership;
	private double hardMembership;
	
	public Recipe(double cTime, double pTime, String n, String dir)
	{
		cookingTime = cTime;
		prepTime = pTime;
		name = n;
		info = dir;
	}
	
	//GETTERS
	public double getCookingTime()
	{
		return cookingTime;
	}
	
	public double getPrepTime()
	{
		return prepTime;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getInfo()
	{
		return info;
	}
	
	public double getEasy()
	{
		return easyMembership;
	}
	
	public double getMed()
	{
		return medMembership;
	}
	
	public double getHard()
	{
		return hardMembership;
	}
	
	public double getTotalTime()
	{
		return prepTime + cookingTime;
	}
	
	//SETTERS
	public void setEasy(double member)
	{
		easyMembership = member;
	}
	
	public void setMed(double member)
	{
		medMembership = member;
	}
	
	public void setHard(double member)
	{
		hardMembership = member;
	}
}
