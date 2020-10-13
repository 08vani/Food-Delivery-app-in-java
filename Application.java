package Project_2;

import java.util.HashMap;
import java.util.Scanner;

public class Application {
	
	
	static CustomerClient c=new CustomerClient();
	static RestaurantClient r=new RestaurantClient();
	
	
	
	
	public static void main (String[] args)
	{
		start();
		
		HashMap<Integer,CustomerTypes> crecords=c.get_crecords();
		
		HashMap<Integer,Restaurant> rrecords=r.getrrecords();
		
		int ch=-1;
		
		Scanner in=new Scanner (System.in);
		
		while(ch!=5)
		{
			displaystartmenu();
			ch=in.nextInt();
			
			if(ch==1)
			{
				r.handlequery(crecords);
			}
			else if(ch==2)
			{
				c.handlequery(rrecords);
			}
			else if(ch==3)
			{
				System.out.println("1. Customer Details");
				System.out.println("2. Restaurant Details");
				
				int val=in.nextInt();
				
				if(val==1)
				{
					c.CheckUserDetails();
				}
				else if(val==2)
				{
					r.CheckUserDetails();
				}
				else
				{
					System.out.println("Invalid option, enter again");
				}
			}
			else if(ch==4)
			{
				System.out.println("Total Company Balance : "+c.get_tcost());
				System.out.println("Total Delivery charges collected : "+c.get_dcost());
			}
			else if(ch==5)
			{
				
			}
			else
			{
				System.out.println("Invalid option, please enter again");
			}
		}
	}
	
	
	public static void start()
	{
		c.add_customers();
		r.add_restaurants();
	}
	
	 
	public static void displaystartmenu()
	{
		System.out.println("Welcome to Zotato");
		System.out.println("1. Enter as Restaurant Owner");
		System.out.println("2. Enter as Customer");
		System.out.println("3. Check User Details");
		System.out.println("4. Company Account Details");
		System.out.println("5. Exit");
	}
	

}
