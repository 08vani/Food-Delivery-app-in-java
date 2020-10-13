package Project_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CustomerClient implements UserTypes{
	
	private HashMap<Integer,CustomerTypes> crecords;
	private float delivarycost;
	private float trasactioncost;
	private CustomerTypes selected;
	
	public float get_dcost() { return delivarycost;}
	public float get_tcost() { return trasactioncost;}
	public  HashMap<Integer,CustomerTypes> get_crecords() { return crecords;} 
	
	CustomerClient()
	{
		crecords=new HashMap<>();
		delivarycost=0;
		trasactioncost=0;
		
	}
	
	
	public void add_customers()
	{
		CustomerTypes c1= new EliteCustomer ("Ram","Delhi");
		CustomerTypes c2= new EliteCustomer ("Sam","Delhi");
		CustomerTypes c3= new SpecialCustomer("Tim","Delhi");
		CustomerTypes c4= new Customer("Kim","Delhi");
		CustomerTypes c5= new Customer("Jim","Delhi");
		crecords.put(1,c1);
		crecords.put(2,c2);
		crecords.put(3,c3);
		crecords.put(4,c4);
		crecords.put(5,c5);
	}
	
	public void CheckUserDetails()
	{
		for(int i=1;i<=5;i++)
		{
			System.out.println(i+" "+crecords.get(i).getname());
		}
		
		Scanner in=new Scanner (System.in);
		int val=in.nextInt();
		
		if(crecords.get(val).getcategory().equals("Normal"))
		{
			System.out.println(crecords.get(val).getname()+" ,"+crecords.get(val).getAdress()+" ,"+crecords.get(val).getamount());
		}
		else
		{
			System.out.println(crecords.get(val).getname()+"("+crecords.get(val).getcategory()+")"+" ,"+crecords.get(val).getAdress()+" ,"+crecords.get(val).getamount());
		}
	}
	
	public void searching (int ch)
	{
		selected =crecords.get(ch);
	}
	
	public void handlequery(HashMap<Integer,Restaurant> rrecord)
	{
		Scanner in=new Scanner (System.in);
		
		displaychoices();
		
		int ch=in.nextInt();
		
		searching(ch);
		
		
		ch=-1;
		
		while(ch!=5) 
		{
			System.out.println("Welcome "+selected.getname());
			displaymenu();
			
			ch=in.nextInt();
			
			if(ch==1)
			{
				rdisplaychoices(rrecord);
				int val=in.nextInt();
				System.out.println("Choose Item by code");
				HashMap<Integer,ItemDetails> d=rrecord.get(val).getfoodoptions();
				
				Set<Map.Entry<Integer, ItemDetails>> enter= d.entrySet();
				
				for(Map.Entry<Integer, ItemDetails>entery:enter) 
				{
					System.out.print(rrecord.get(val).getname()+" ");
					System.out.println(entery.getValue().toString());
				}
				
				int item=in.nextInt();
				
				selected.addCart(rrecord.get(val).getname(),d.get(item),val);
			}
			else if(ch==2)
			{
				String[] arr=selected.transaction(rrecord).split(" ");
				delivarycost+=Float.parseFloat(arr[1]);
				trasactioncost+=Float.parseFloat(arr[0]);
			}
			else if(ch==3)
			{
				System.out.println("Total Reward :"+selected.getreward());
			}
			else if(ch==4)
			{
				selected.PrintHistory();
			}
			else if (ch!=5)
			{
				System.out.println("Invalid option,select again");
			}
		
			
		}
		
		
	}

	public static void displaymenu() {
		
		
		System.out.println("Customer Menu");
		System.out.println("1. Select Restaurant");
		System.out.println("2. Checkout cart");
		System.out.println("3. Reward won");
		System.out.println("4. Print the recent order");
		System.out.println("5. Exit");
		
	}
	
	public void rdisplaychoices(HashMap<Integer,Restaurant> rrecords)
	{
		System.out.println("Choose Restaurant");
		//System.out.println(rrecords.size());
		for(int i=0;i<rrecords.size();i++)
		{
			if(rrecords.get(i+1).gettype().equals("Normal"))
				System.out.println(i+1+" "+rrecords.get(i+1).getname());
			else
				System.out.println(i+1+" "+rrecords.get(i+1).getname()+" ( "+rrecords.get(i+1).gettype()+" )");
		}
	}
	
	
	
	
	
	public void displaychoices() {
		for(int i=1;i<=5;i++)
		{
			if(crecords.get(i).getcategory().equals("Normal"))
				System.out.println(i+" "+crecords.get(i).getname());
			else
				System.out.println(i+" "+crecords.get(i).getname()+" ( "+crecords.get(i).getcategory()+" )");
		}
		
	}
	
	
	
	
	
	
	
	

}
