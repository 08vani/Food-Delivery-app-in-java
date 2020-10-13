package Project_2;

import java.util.*;

public class RestaurantClient  implements UserTypes{
	
	private HashMap<Integer,Restaurant> rrecords;
	Restaurant selected;
	
	public HashMap<Integer,Restaurant> getrrecords(){ return rrecords;}
	

	public void add_restaurants()
	{
		rrecords=new HashMap<>();
		Restaurant r1= new Restaurant("Shah's","Delhi","Authentic");
		Restaurant r2= new Restaurant ("Ravi's","Delhi","Normal");
		Restaurant r3= new Restaurant ("The Chinese","Delhi","Authentic");
		Restaurant r4= new Restaurant ("Wang's","Delhi","FastFood");
		Restaurant r5= new Restaurant ("Paradise","Delhi","Normal");
		rrecords.put(1,r1);
		rrecords.put(2,r2);
		rrecords.put(3, r3);
		rrecords.put(4, r4);
		rrecords.put(5, r5);
		
		
	}
	
	public void searching(int ch)
	{
		selected=rrecords.get(ch);
	}
	public void handlequery(HashMap<Integer,CustomerTypes> crecords)
	{
		Scanner in=new Scanner (System.in);
		
		
		
		displaychoices();
		
		int ch=in.nextInt();
		
		searching(ch);
		
		ch=-1;
		
		while(ch!=5)
		{
			displaymenu(selected.getname());
			
			ch=in.nextInt();
	
			if(ch==1)
			{
				selected.add_item();
			}
			else if(ch==2)
			{
				selected.edit_item(selected.getname());
			}
			else if(ch==3)
			{
				System.out.println("Rewards Points: "+selected.getreward());
			}
			else if(ch==4)
			{
				if(selected.gettype().equals("Normal"))
				{
					System.out.println("It is not applicable ");
				}
				else
				{
					System.out.println("Offer on bill value");
					selected.setdiscount(in.nextInt());
				}
			}
			else if(ch!=5)
			{
				System.out.println("Invalid Input, Please enter again");
			}
			
		}
		
		
	}
	
	public void displaychoices()
	{
		System.out.println("Choose Restaurant");
		for(int i=0;i<5;i++)
		{
			if(rrecords.get(i+1).gettype().equals("Normal"))
				System.out.println(i+1+" "+rrecords.get(i+1).getname());
			else
				System.out.println(i+1+" "+rrecords.get(i+1).getname()+" ( "+rrecords.get(i+1).gettype()+" )");
		}
		
	}
	public void displaymenu(String n)
	{
		System.out.println("Welcome "+n);
		System.out.println("1. Add item");
		System.out.println("2. Edit item");
		System.out.println("3. Print Rewards");
		System.out.println("4. Discount on bill value");
		System.out.println("5. Exit");
	}
	
	public void CheckUserDetails()
	{
		for(int i=0;i<rrecords.size();i++)
		{
				System.out.println(i+1+" "+rrecords.get(i+1).getname());
			
		}

		Scanner in=new Scanner (System.in);
		int val=in.nextInt();
		if(rrecords.get(val).gettype().equals("Normal"))
		{
			System.out.println(rrecords.get(val).getname()+" ,"+rrecords.get(val).getadress()+" ,"+rrecords.get(val).getordercount());
		}
		else
		{
			System.out.println(rrecords.get(val).getname()+"("+rrecords.get(val).gettype()+")"+" ,"+rrecords.get(val).getadress()+" ,"+rrecords.get(val).getordercount());
		}
	}

}
