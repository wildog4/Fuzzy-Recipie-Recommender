package thesis;

public class Membership 
{
	public static final int GAMMA = 0;
	public static final int Z = 1;
	public static final int TRIANGLE = 2;
	public static final int TRAPEZOID = 3;
	public static final int S = 4;
	public static final int MAX = 5;
	
	double[] variables;		//Contains all the variables needed for the given type.
	double[] boundries = new double[2];		//What values the fuzzy variable can be between the [0] must be less than or equal to variables[0] and
											//[1] must be greater than or equal to variables[variables.length-1]
	int type;				//Tells what type of membership function it is 0 is Gamma
							//1 is a Z function
							//2 is the triangle function
							//3 is the trapezoid function
							//4 is the S function
		
	//This will create a membership function based on the integer given
	public Membership(int t, double[] vars, double[] bound, double mod)
	{
		if(bound.length != 2)
		{
			System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. INVALID BOUNDRIES. MUST BE AN ARRAY OF LENGTH 2.");
			System.exit(1);
		}
		
		type = t;
		if(t < 0 || t > MAX-1)
		{
			System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. ID MUST BE BETWEEN 0 and " + (MAX-1));
			System.exit(1);
		}
		
		switch(t)
		{
			case GAMMA:
			case Z:
				if(vars.length != 2)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 2 VARIABLES TO DECLARE A GAMMA OR Z FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
			case TRIANGLE:
				if(vars.length != 3)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 3 VARIABLES TO DECLARE A TRIANGLE FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1] || vars[0] > vars[2] || vars[1] > vars[2])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
			case TRAPEZOID:
				if(vars.length != 4)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 4 VARIABLES TO DECLARE A TRAPEZOID FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1] || vars[0] > vars[2] || vars[0]>vars[3] || vars[1] > vars[2] || vars[1]>vars[3] || vars[2] > vars[3])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
			case S:
				if(vars.length != 3)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 3 VARIABLES TO DECLARE AN S FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1] || vars[0] > vars[2] || vars[1] > vars[2])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
		}
		if(bound[0]>vars[0] || bound[1]<vars[vars.length-1])
		{
			System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. BOUNDRIES MUST BE VALID BOUNDRIES. YOU MUST HAVE YOUR BOUNDRIES WITHIN");
			System.err.println("YOUR DECLARED VARIABLES.");
			System.exit(1);
		}
		
		variables = new double[vars.length];
		for(int i = 0; i < vars.length; i++)
		{
			variables[i] = (vars[i] + mod);
		}
	}
	
	
	//A second Constructor that doesn't take a modifier. It initializes it as if it was 0.
	public Membership(int t, double[] vars, double[] bound)
	{
		if(bound.length != 2)
		{
			System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. INVALID BOUNDRIES. MUST BE AN ARRAY OF LENGTH 2.");
			System.exit(1);
		}
		
		type = t;
		if(t < 0 || t > MAX-1)
		{
			System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. ID MUST BE BETWEEN 0 and " + (MAX-1));
			System.exit(1);
		}
		switch(t)
		{
			case GAMMA:
			case Z:
				if(vars.length != 2)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 2 VARIABLES TO DECLARE A GAMMA OR Z FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
			case TRIANGLE:
				if(vars.length != 3)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 3 VARIABLES TO DECLARE A TRIANGLE FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1] || vars[0] > vars[2] || vars[1] > vars[2])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
			case TRAPEZOID:
				if(vars.length != 4)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 4 VARIABLES TO DECLARE A TRAPEZOID FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1] || vars[0] > vars[2] || vars[0]>vars[3] || vars[1] > vars[2] || vars[1]>vars[3] || vars[2] > vars[3])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
			case S:
				if(vars.length != 3)
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. YOU MUST SEND 3 VARIABLES TO DECLARE AN S FUNCTION.");
					System.exit(1);
				}
				if(vars[0]>vars[1] || vars[0] > vars[2] || vars[1] > vars[2])
				{
					System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. VARIABLES MUST BE IN ASCENDING ORDER.");
					System.exit(1);
				}
				break;
		}
		if(bound[0]>vars[0] || bound[1]<vars[vars.length-1])
		{
			System.err.println("ERROR DECLARING MEMBERSHIP FUNCTION. BOUNDRIES MUST BE VALID BOUNDRIES. YOU MUST HAVE YOUR BOUNDRIES WITHIN");
			System.err.println("YOUR DECLARED VARIABLES.");
			System.exit(1);
		}
		
		variables = new double[vars.length];
		for(int i = 0; i < vars.length; i++)
		{
			variables[i] = vars[i];
		}
	}
	
	public double membershipDegree(double crisp)
	{
		switch(type)
		{
			
		//This case is for gamma membership functions
		//variables[0] is Alpha and variables[1] is Beta
			case GAMMA:
				if(crisp<(variables[0]))
				{
					//System.out.println("Here.");
					return 0;
				}
				else if(crisp>=variables[1])
				{
					//System.out.println("Here1.");
					return 1;
				}
				else
				{
					//System.out.println("Here2.");
					return (crisp-variables[0])/(variables[1] - variables[0]);
				}
			
			//This case is for Z-membership function	
			//variables[0] is Alpha and variables[1] is Beta
			case Z:
				if(crisp < variables[0])
				{
					return 1;
				}
				else if(crisp>=variables[1])
				{
					//System.out.println("Here1.");
					return 0;
				}
				else
				{
					//System.out.println("Here2.");
					return 1 - (crisp-variables[0])/(variables[1] - variables[0]);
				}
				
			//This case is for triangle membership function	
			//variables[0] is Alpha (left bound), variables[1] is Beta (middle bound) and variables[2] is Gamma (right bound)
			case TRIANGLE:
				if(crisp < variables[0])
				{
					return 0;
				}
				else if(variables[0] < crisp && crisp <= variables[1])
				{
					//System.out.println("Here.");
					return ((crisp-variables[0])/(variables[1]-variables[0]));
				}
				else if(variables[1] < crisp && crisp < variables[2])
				{
					//System.out.println("Here.");
					return ((variables[2]-crisp)/(variables[2]-variables[1]));
				}
				else
				{
					return 0;
				}
				
			//This case is for trapezoid membership function	
			//variables[0] is Alpha (left bound), variables[1] is Beta (middle-left bound) and variables[2] is Gamma (middle-right bound)
			//variable[3] is sigma (right bound)
			case TRAPEZOID:
				if(crisp < variables[0])
				{
					return 0;
				}
				else if(variables[0] < crisp && crisp < variables[1])
				{
					//System.out.println("Here.");
					return ((crisp-variables[0])/(variables[1]-variables[0]));
				}
				else if(variables[1] <= crisp && crisp <= variables[2])
				{
					return 1;
				}
				else if(variables[2] < crisp && crisp < variables[3])
				{
					//System.out.println("Here.");
					return ((variables[3]-crisp)/(variables[3]-variables[2]));
				}
				else
				{
					return 0;
				}
			
			//This case is for S membership function to mimic a graph that looks like an S	
			//variables[0] is Alpha (left bound), variables[1] is Beta (middle bound) and variables[2] is Gamma (right bound)	
			case S:
				if(crisp < variables[0])
				{
					return 0;
				}
				else if(variables[0] < crisp && crisp < variables[1])
				{
					return 2 * (Math.pow((crisp - variables[0])/ (variables[2] - variables[0]), 2));
				}
				else if(variables[1] <= crisp && crisp < variables[2])
				{
					return Math.abs(1 - 2 * (Math.pow((crisp - variables[0])/ (variables[2] - variables[0]), 2)));
				}
				else
				{
					return 1;
				}
		}
		return -1.0;
	}

	public double centroidDefuzzify() 
	{
		double areaSum = 0;
		double centroidSum = 0;
		double centroid;
		switch(type)
		{
			case GAMMA:
				areaSum = Math.abs(variables[0]*variables[1]*.5) + Math.abs(boundries[1]-variables[1]);
				centroidSum = ((variables[0]*2) + variables[1]/3);
				centroid = areaSum/centroidSum;
				return centroid;
			case Z:
				areaSum = Math.abs(variables[0]*variables[1]*.5) + Math.abs(variables[0] - boundries[0]);
				centroidSum = ((variables[0]*2) + variables[1]/3);
				centroid = areaSum/centroidSum;
				return centroid;
			case TRIANGLE:
				for(int i=0; i<2; i++)
				{
					areaSum += Math.abs(variables[i]*variables[i+1]*.5);
				}
				centroidSum += ((variables[0]+ (2 * variables[1]))/3) + ((variables[2]+ (2 * variables[1]))/3);
				centroid = areaSum/centroidSum;
				return centroid;
			case TRAPEZOID:
				areaSum = Math.abs(((variables[2]-variables[1]) + (variables[3]-variables[0]))/2);
				for(int i = 0; i < 4; i++)
				{
					centroidSum += variables[i]/4;
				}
				centroid = areaSum/centroidSum;
				return centroid;
			case S:
				return -1;
		}
		
		return -2;
	}
}
