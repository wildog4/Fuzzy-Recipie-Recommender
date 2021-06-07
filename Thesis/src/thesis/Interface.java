package thesis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Interface 
{
	public static final int GAMMA_FUNC_ID = 0;		//Variables to keep track of which function maps to which ID
	public static final int Z_FUNC_ID = 1;
	public static final int TRIANGLE_FUNC_ID = 2;
	public static final int TRAPEZOID_FUNC_ID = 3;
	public static final int S_FUNC_ID = 4;
	public static final int TIME_BOUND = 500;
	
	public static final double TIRED_MEMBERSHIP_GRADE = 0.3;		//Variables to keep track of the base membership grade when given these words.
	public static final double EXHAUSTED_MEMBERSHIP_GRADE = 0;
	public static final double ENERGETIC_MEMBERSHIP_GRADE = 1;
	public static final double FATIGUED_MEMBERSHIP_GRADE = 0.1;
	public static final double GOOD_MEMBERSHIP_GRADE = 0.7;
	
	public static final double HUNGRY_MEMBERSHIP_GRADE = 0.76;
	public static final double STARVING_MEMBERSHIP_GRADE = 1;
	public static final double FULL_MEMBERSHIP_GRADE = 0;
	public static final double REALLY_MEMBERSHIP_GRADE = 0.8;
	public static final double FAMISHED_MEMBERSHIP_GRADE = 0.8;
	
	public static final double LOT_MEMBERSHIP_GRADE = 1;
	public static final double BUSY_MEMBERSHIP_GRADE = 0;
	public static final double LITTLE_MEMBERSHIP_GRADE = 0.2;
	public static final double OPEN_MEMBERSHIP_GRADE = 0.8;
	public static final double AVAILABLE_MEMBERSHIP_GRADE = 0.6;
	public static final double HECTIC_MEMBERSHIP_GRADE = 0.3;
	public static final double EVENTFUL_MEMBERSHIP_GRADE = 0.35;
	
	public static final int NUM_RESULTS = 3;			//How many of the recipes will be displayed
	

	public void run()
	{
//		double[] vars = {0,1,2};
//		double[] bound = {-2,4};
//		Fuzzy test = new Fuzzy("test" , S_FUNC_ID, vars, bound);
		
//		System.out.println("The membership grade of 0 is: " + test.membershipGrade(0));
//		System.out.println("The membership grade of 1 is: " + test.membershipGrade(1));
//		System.out.println("The membership grade of .999 is: " + test.membershipGrade(.999));
//		System.out.println("The membership grade of 2 is: " + test.membershipGrade(2));
		
		Recipe[] recipes = initRecipes();
		
		double[] bounds = {0,TIME_BOUND};
		
		double[] easyVars = {10,30};
		
		Fuzzy easy = new Fuzzy("Easy" , Z_FUNC_ID, easyVars, bounds);
		
		double[] medVars = {10,20,40,60};
		Fuzzy med = new Fuzzy("Medium" , TRAPEZOID_FUNC_ID, medVars, bounds);
		
		double[] hardVars = {40,100};
		Fuzzy hard = new Fuzzy("Hard" , GAMMA_FUNC_ID, hardVars, bounds);
		
//		System.out.println("The membership easy grade of 10 is: " + easy.membershipGrade(10));
//		System.out.println("The membership easy grade of 20 is: " + easy.membershipGrade(20));
//		System.out.println("The membership easy grade of 40 is: " + easy.membershipGrade(40));
//		System.out.println("The membership easy grade of 60 is: " + easy.membershipGrade(60));
		
//		System.out.println("The membership medium grade of 13 is: " + med.membershipGrade(13));
//		System.out.println("The membership medium grade of 20 is: " + med.membershipGrade(20));
//		System.out.println("The membership medium grade of 42 is: " + med.membershipGrade(42));
//		System.out.println("The membership medium grade of 60 is: " + med.membershipGrade(60));
		
//		System.out.println("The membership hard grade of 10 is: " + hard.membershipGrade(10));
//		System.out.println("The membership hard grade of 20 is: " + hard.membershipGrade(20));
//		System.out.println("The membership hard grade of 42 is: " + hard.membershipGrade(42));
//		System.out.println("The membership hard grade of 64 is: " + hard.membershipGrade(64));
	
		double totalTime;
		for(Recipe r:recipes)
		{
			totalTime = r.getCookingTime() + r.getPrepTime();
//			System.out.println("For recipe: " + r.getName() + " it is " + easy.membershipGrade(totalTime) + " Easy.");
//			System.out.println("For recipe: " + r.getName() + " it is " + med.membershipGrade(totalTime) + " Medium.");
//			System.out.println("For recipe: " + r.getName() + " it is " + hard.membershipGrade(totalTime) + " Hard.");
			r.setEasy(easy.membershipGrade(totalTime));
			r.setMed(med.membershipGrade(totalTime));
			r.setHard(hard.membershipGrade(totalTime));
		}
		
		Scanner keyboard = new Scanner(System.in);
		String input = "";
		
		System.out.println("Welcome to the Cooking Recipe Recommender! This is going to ask you a few questions and based on your answers, ");
		System.out.println("reccomend the best recipes to cook for dinner.\n\n");
		
		double energyMem = 0;
		double appetiteMem = 0;
		double spareMem = 0;
		
		User usr;
		Recipe[] results;
		
		while(!input.equals("YES"))
		{
//			while(appetiteMem==0)
//			{
			System.out.println("How are you feeling today? Please type your response then hit the Enter key.");
			input = keyboard.nextLine();
			energyMem = getEnergyMem(input);
			System.out.println("Energy Membership: " + energyMem + "\n");
//			}
			
			
			
			System.out.println("How hungry do you feel right now? Please type your response then hit the Enter key.");
			input =  keyboard.nextLine();
			appetiteMem = getAppetiteMem(input);
			System.out.println("Appetite Membership: " + appetiteMem + "\n");
//			System.out.println(input);
			
			System.out.println("How much time do you have right now? Please type your response then hit the Enter key.");
			input =  keyboard.nextLine();
			input = input.toUpperCase();
			spareMem = getSpareMem(input);
			System.out.println("Spare Membership: " + spareMem + "\n");
//			System.out.println(input);
			
			usr = new User(energyMem, appetiteMem, spareMem);
			
			results = evaluate(usr,recipes);
//			System.out.println(results.length);
			System.out.println("\n\n\n");
			for(int i = 0; i < NUM_RESULTS; i++)
			{
				if(i==0)
				{
					System.out.println("The best recipe is: " + results[i].getName());
					System.out.println("Cooking Time: " + results[i].getCookingTime());
					System.out.println("Prep Time: " + results[i].getPrepTime());
					System.out.println(results[i].getInfo());		
				}
				else if(i==1)
				{
					System.out.println("The 2nd best recipe is: " + results[i].getName());
					System.out.println("Cooking Time: " + results[i].getCookingTime());
					System.out.println("Prep Time: " + results[i].getPrepTime());
					System.out.println(results[i].getInfo());
				}
				else if(i==2)
				{
					System.out.println("The 3rd best recipe is: " + results[i].getName());
					System.out.println("Cooking Time: " + results[i].getCookingTime());
					System.out.println("Prep Time: " + results[i].getPrepTime());
					System.out.println(results[i].getInfo());
				}
				else
				{
					System.out.println("The " + (i+1)  + "th best recipe is: " + results[i].getName());
					System.out.println("Cooking Time: " + results[i].getCookingTime());
					System.out.println("Prep Time: " + results[i].getPrepTime());
					System.out.println(results[i].getInfo());
				}
				System.out.println("\n\nPlease press enter for another recipe..");
				keyboard.nextLine();
				System.out.println("\n\n");
			}
			
			System.out.print("Are you finished with this demo? If so type 'yes': ");
			input =  keyboard.nextLine();
			input = input.toUpperCase();
			System.out.println("\n\n\n");
			
		}
	}

	private Recipe[] evaluate(User usr, Recipe[] recipes) 
	{
		Recipe[] results = new Recipe[NUM_RESULTS];
		
		int[] positionsAdded = new int[NUM_RESULTS];
		for(int i = 0; i < NUM_RESULTS; i++)
		{
			positionsAdded[i] = -1;
		}
		
		boolean quick, average, lengthy;
		
		if(usr.getEnergy() < 0.2)
		{
			quick = true;
			average = false;
			lengthy = false;
		}
		else if(usr.getEnergy() < 0.6)
		{
			if(usr.getAppetite() < 0.3)
			{
				if(usr.getSpareTime() < 0.3)
				{
					quick = true;
					average = true;
					lengthy = false;
				}
				else if(usr.getSpareTime() > 0.6)
				{
					quick = false;
					average = true;
					lengthy = true;
				}
				else
				{
					quick = true;
					average = true;
					lengthy = false;
				}
			}
			else if(usr.getAppetite() > 0.7)
			{
				quick = true;
				average = true;
				lengthy = false;
			}
			else
			{
				if(usr.getSpareTime() < 0.3)
				{
					quick = true;
					average = true;
					lengthy = false;
				}
				else if(usr.getSpareTime() > 0.6)
				{
					quick = false;
					average = true;
					lengthy = true;
				}
				else
				{
					quick = true;
					average = true;
					lengthy = false;
				}
			}
		}
		else if(usr.getEnergy() < 0.8)
		{
			if(usr.getAppetite() < 0.3)
			{
				if(usr.getSpareTime() < 0.3)
				{
					quick = true;
					average = true;
					lengthy = false;
				}
				else if(usr.getSpareTime() > 0.6)
				{
					quick = false;
					average = false;
					lengthy = true;
				}
				else
				{
					quick = true;
					average = true;
					lengthy = false;
				}
			}
			else if(usr.getAppetite() > 0.7)
			{
				quick = true;
				average = true;
				lengthy = false;
			}
			else
			{
				if(usr.getSpareTime() < 0.3)
				{
					quick = true;
					average = true;
					lengthy = false;
				}
				else if(usr.getSpareTime() > 0.6)
				{
					quick = false;
					average = true;
					lengthy = true;
				}
				else
				{
					quick = true;
					average = true;
					lengthy = false;
				}
			}
		}
		else
		{
			if(usr.getAppetite() < 0.3)
			{
				if(usr.getSpareTime() < 0.3)
				{
					quick = true;
					average = true;
					lengthy = false;
				}
				else if(usr.getSpareTime() > 0.6)
				{
					quick = false;
					average = true;
					lengthy = true;
				}
				else
				{
					quick = true;
					average = true;
					lengthy = false;
				}
			}
			else if(usr.getAppetite() > 0.7)
			{
				quick = true;
				average = true;
				lengthy = false;
			}
			else
			{
				if(usr.getSpareTime() < 0.3)
				{
					quick = true;
					average = true;
					lengthy = false;
				}
				else if(usr.getSpareTime() > 0.6)
				{
					quick = false;
					average = true;
					lengthy = true;
				}
				else
				{
					quick = true;
					average = true;
					lengthy = false;
				}
			}
		}
		
		int bestPosition = -1;
		
		System.out.println("Quick: " + quick);
		System.out.println("Average: " + average);
		System.out.println("Lengthy: " + lengthy);
		
		for(int i = 0; i < NUM_RESULTS; i++)
		{
			
			if(quick && average && lengthy)
			{
				while(contains(bestPosition, positionsAdded))
				{
					bestPosition = (int)(Math.random()*recipes.length);
				}
				
				results[i] = recipes[bestPosition];
			}
			else if(quick && average && !lengthy)
			{
				bestPosition = 0;
				
				for(int j = 1; j < recipes.length; j++)
				{
//					for(int k = 0; k <positionsAdded.length; k++)
//					{
//						System.out.println("positionsAdded[" + k +"] is: " + positionsAdded[k]);
//					}
//					System.out.println("!contains("+ j + ", positionsAdded): " + !contains(j, positionsAdded));
					if((recipes[j].getEasy()>recipes[bestPosition].getEasy() || recipes[j].getMed()>recipes[bestPosition].getMed())
							&& !contains(j, positionsAdded))
					{
						
						bestPosition = j;
					}
				}
				
				results[i] = recipes[bestPosition]; 
			}
			else if(quick && !average && lengthy)
			{
				bestPosition = 0;
				
				for(int j = 1; j < recipes.length; j++)
				{
					if((recipes[j].getEasy()>recipes[bestPosition].getEasy() || recipes[j].getHard()>recipes[bestPosition].getHard())
							&& !contains(j, positionsAdded))
					{

						bestPosition = j;
					}
				}
				
				results[i] = recipes[bestPosition]; 
			}
			else if(quick && !average && !lengthy)
			{
				bestPosition = 0;
				
				for(int j = 1; j < recipes.length; j++)
				{
					if(recipes[j].getEasy()>recipes[bestPosition].getEasy()
							&& !contains(j, positionsAdded))
					{
						bestPosition = j;
					}
				}
				
				results[i] = recipes[bestPosition]; 
			}
			else if(!quick && average && lengthy)
			{
				bestPosition = 0;
				
				for(int j = 1; j < recipes.length; j++)
				{
					if((recipes[j].getMed()>recipes[bestPosition].getMed() || recipes[j].getHard()>recipes[bestPosition].getHard())
							&& !contains(j, positionsAdded))
					{
						bestPosition = j;
					}
				}
				
				results[i] = recipes[bestPosition]; 
			}
			else if(!quick && average && !lengthy)
			{
				bestPosition = 0;
				
				for(int j = 1; j < recipes.length; j++)
				{
					if(recipes[j].getMed()>recipes[bestPosition].getMed()
							&& !contains(j, positionsAdded))
					{
						if(!contains(bestPosition, positionsAdded))
						{
							bestPosition = j;
						}
					}
				}
				
				results[i] = recipes[bestPosition]; 
			}
			else if(!quick && !average && lengthy)
			{
				bestPosition = 0;
				
				for(int j = 1; j < recipes.length; j++)
				{
					if(recipes[j].getHard()>recipes[bestPosition].getHard()
							&& !contains(j, positionsAdded))
					{
						if(!contains(bestPosition, positionsAdded))
						{
							bestPosition = j;
						}
					}
				}
				
				results[i] = recipes[bestPosition]; 
			}
			else
			{
				while(contains(bestPosition, positionsAdded))
				{
					bestPosition = (int)(Math.random()*recipes.length);
				}
				
				results[i] = recipes[bestPosition]; 
			}
			
			positionsAdded[i] = bestPosition;
		}
		
		return results;
	}
	

	private boolean contains(int integer, int[] arr) 
	{
//		System.out.println("length " + arr.length);
		
		for(int i = 0; i < arr.length; i++)
		{
//			System.out.println("integer: " + integer);
			
//			System.out.println("arr[i] " + arr[i]);
			if(integer == arr[i])
			{
				return true;
			}
		}
		
		return false;
	}

	private double getSpareMem(String input) 
	{
		String[] split = input.replaceAll("[^a-zA-Z ]", "").toUpperCase().split("\\s+");
		
		for(int i = 0; i < split.length; i++ )
		{
			if(split[i].equals("LOT"))
			{
				
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-LOT_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return LOT_MEMBERSHIP_GRADE - 0.3;
					}
					else if(split[i-1].equals("VERY"))
					{
						return LOT_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return LOT_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return LOT_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return LOT_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("BUSY"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-BUSY_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return BUSY_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return BUSY_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return BUSY_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return BUSY_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return BUSY_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("LITTLE"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-LITTLE_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return LITTLE_MEMBERSHIP_GRADE + 0.2;
					}
					else if(split[i-1].equals("VERY"))
					{
						return LITTLE_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return LITTLE_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return LITTLE_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return LITTLE_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("OPEN"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-OPEN_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return OPEN_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return OPEN_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return OPEN_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return OPEN_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return OPEN_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("AVAILABLE"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-AVAILABLE_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return AVAILABLE_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return AVAILABLE_MEMBERSHIP_GRADE + 0.05;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return AVAILABLE_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return AVAILABLE_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return AVAILABLE_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("HECTIC"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-HECTIC_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return HECTIC_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return HECTIC_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return HECTIC_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return HECTIC_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return HECTIC_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("EVENTFUL"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-EVENTFUL_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return EVENTFUL_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return EVENTFUL_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return EVENTFUL_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return EVENTFUL_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return EVENTFUL_MEMBERSHIP_GRADE;
				}
			}
		}
		
		return 0.5;
	}

	private double getAppetiteMem(String input) 
	{
		String[] split = input.replaceAll("[^a-zA-Z ]", "").toUpperCase().split("\\s+");
		
		for(int i = 0; i < split.length; i++ )
		{
			if(split[i].equals("HUNGRY"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-HUNGRY_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return HUNGRY_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return HUNGRY_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return HUNGRY_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return HUNGRY_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return HUNGRY_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("STARVING"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-STARVING_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return STARVING_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return STARVING_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return STARVING_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return STARVING_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return STARVING_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("FULL"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-FULL_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return FULL_MEMBERSHIP_GRADE + 0.2;
					}
					else if(split[i-1].equals("VERY"))
					{
						return FULL_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return FULL_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return FULL_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return FULL_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("REALLY"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-REALLY_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return REALLY_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return REALLY_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return REALLY_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return REALLY_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return REALLY_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("FAMISHED"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-FAMISHED_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return FAMISHED_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return FAMISHED_MEMBERSHIP_GRADE + 0.05;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return FAMISHED_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return FAMISHED_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return FAMISHED_MEMBERSHIP_GRADE;
				}
			}
		}
		
		return 0.5;
	}

	private double getEnergyMem(String input) 
	{
		String[] split = input.replaceAll("[^a-zA-Z ]", "").toUpperCase().split("\\s+");
		
		for(int i = 0; i < split.length; i++ )
		{
			if(split[i].equals("TIRED"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-TIRED_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return TIRED_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return TIRED_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return TIRED_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return TIRED_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return TIRED_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("EXHAUSTED"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-EXHAUSTED_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return EXHAUSTED_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return EXHAUSTED_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return EXHAUSTED_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return EXHAUSTED_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return EXHAUSTED_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("ENERGETIC"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-ENERGETIC_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return ENERGETIC_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return ENERGETIC_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return ENERGETIC_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return ENERGETIC_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return ENERGETIC_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("GOOD"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-GOOD_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return GOOD_MEMBERSHIP_GRADE - 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return GOOD_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return GOOD_MEMBERSHIP_GRADE - 0.2;
					}
					else
					{
						return GOOD_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return GOOD_MEMBERSHIP_GRADE;
				}
			}
			if(split[i].equals("FATIGUED"))
			{
				if(i>0) 
				{
					if(split[i-1].equals("NOT"))
					{
						return 1-FATIGUED_MEMBERSHIP_GRADE;
					}
					else if(split[i-1].equals("KINDA"))
					{
						return FATIGUED_MEMBERSHIP_GRADE + 0.1;
					}
					else if(split[i-1].equals("VERY"))
					{
						return FATIGUED_MEMBERSHIP_GRADE - 0.05;
					}
					else if(split[i-1].equals("HARDLY"))
					{
						return FATIGUED_MEMBERSHIP_GRADE + 0.2;
					}
					else
					{
						return FATIGUED_MEMBERSHIP_GRADE;
					}
				}
				else
				{
					return FATIGUED_MEMBERSHIP_GRADE;
				}
			}
		}
		
		return 0.5;
	}

	// This is used to initialize every recipe in the Recipes.txt file
	private Recipe[] initRecipes()
	{
		try
		{
			Path fileName = Path.of("C:/Users/William Csont/eclipse-workspace/Thesis/Recipes.txt");
         
        	String recipeFile = Files.readString(fileName);
//        	System.out.println(recipeFile);
        	
        	
        	int index = recipeFile.indexOf("NUMBER OF RECIPES:") + 18;
        	int i=0;
        	String numRecipesStr = "";
        	while(recipeFile.charAt(index+i)!='\n')
        	{
//        		System.out.println("THE CHARACTER BEING READ IS: " + recipeFile.charAt(index + 18 + i));
//        		System.out.println("THE ASCII VALUE BEING READ IS: " + (int)(recipeFile.charAt(index + 18 + i)));
//        		System.out.println("THE ASCII VALUE of \\n IS: " + (int)('\n'));
        		if(recipeFile.charAt(index+i) != '\r')
        		{
        			numRecipesStr += recipeFile.charAt(index + i);
        		}
        		i++;
        	}
        	
        	index += i+1;
        	
        	int numRecipes = Integer.parseInt(numRecipesStr);
        	
        	int j = 0;
        	String name = "", cookStr = "", prepStr = "", info = "";
        	double cook, prep;
        	
//        	System.out.println("NUMBER OF RECIPES: " + numRecipes);
        	Recipe[] recipes = new Recipe[numRecipes];
        	
        	for(i = 0; i < numRecipes; i++)
        	{
        		index = recipeFile.indexOf("NAME:", index);
        		j = 5;
        		
        		while(recipeFile.charAt(index+j)!='\n')
            	{
            		if(recipeFile.charAt(index+j) != '\r')
            		{
            			name += recipeFile.charAt(index + j);
            		}
            		
            		j++;
            	}
        		
        		index += j+1;
        		j = 5;
        		
        		index = recipeFile.indexOf("COOK:", index);
        		
        		while(recipeFile.charAt(index+j)!='\n')
            	{
            		if(recipeFile.charAt(index+j) != '\r')
            		{
            			cookStr += recipeFile.charAt(index + j);
            		}
            		
            		j++;
            	}
        		cook = Double.parseDouble(cookStr);
        		
        		index += j+1;
        		j = 5;
        		
        		index = recipeFile.indexOf("PREP:", index);
        		
        		while(recipeFile.charAt(index+j)!='\n')
            	{
            		if(recipeFile.charAt(index+j) != '\r')
            		{
            			prepStr += recipeFile.charAt(index + j);
            		}
            		
            		j++;
            	}
        		prep = Double.parseDouble(prepStr);
        		
        		info = recipeFile.substring(recipeFile.indexOf("/*", index) + 4 , recipeFile.indexOf("*/", index)-1);
        		index = recipeFile.indexOf("*/", index);
        		
//        		System.out.println("Name is: " + name );
//        		System.out.println("Cook time is: " + cook + " minutes.");
//        		System.out.println("Prep time is: " + prep + " minutes.");
//        		System.out.println("Info is: " + info);
//        		System.out.println("Index is at: " + index);
        		
        		
        		recipes[i] = new Recipe(cook, prep, name, info);
        		
        		name = "";
        		cookStr = "";
        		prepStr = "";
        		info = "";
        	}
        	
        	return recipes;
        		
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		return null;
	}
}
