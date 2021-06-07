package thesis;

public class Fuzzy 
{
	//Contians the membership function of the fuzzy variable
	private String name;
	private Membership function;
	
	//n is the name of the variable, int represents which function to use and vars are the variables used.
	public Fuzzy(String n, int func, double[] vars, double[] bound)
	{
		name = n;
		function = new Membership(func, vars, bound);
	}
	
	public Fuzzy(String n, int func, double[] vars, double[] bound, double mod)
	{
		name = n;
		function = new Membership(func, vars, bound, mod);
	}
	
	
	//Setter for name
	public void setName(String n)
	{
		name = n;
	}
	
	
	//Getter for name
	public String getName()
	{
		return name;
	}
	
	public double membershipGrade(double crisp)
	{
		return function.membershipDegree(crisp);
	}
	
	public double defuzzify()
	{
		return function.centroidDefuzzify();
	}
}
