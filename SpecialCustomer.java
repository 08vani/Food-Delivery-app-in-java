package Project_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SpecialCustomer extends Customer {
	
	SpecialCustomer(String n,String a)
	{
		super(n,a);
		setCategory("Special");
	}
//	
//	HashMap<ItemDetails, String> cart=super.getcart();
//	float reward=super.getreward();
//	ArrayList<String> history=super.gethistory();
//	float amount=super.getamount();
	public String transaction(HashMap<Integer,Restaurant> rrecords)
	{
		Scanner in= new Scanner (System.in);
		String name="";
		Boolean flag=false;
		int quantity=0;
		float price=0;
		float offer=0;
		Set<Map.Entry<ItemDetails, String>> enteries= super.getcart().entrySet();
		
		for(Map.Entry<ItemDetails, String>entery:enteries)
		{
			
			String[] e= entery.getValue().split(" ");
			if(name.length()==0)
				name=e[0];
			if(name.equals(e[0])==false)
				flag=true;
			System.out.println(entery.getValue());
			
		}
		
		int id=Integer.parseInt(String.valueOf(name.charAt(0)));
		Restaurant rec=rrecords.get(id);
		float end_amount=0;
		int endall=0;
		float final_amount=0;
		float temp=0;
		int reward_earned=0;
		double trancation_cost=0;
		int qcounter=0;
		
		if(flag==true)
		{
			System.out.println("Order consist of different Restaurants");
			System.out.println("1. Remove Item From Cart");
			//revmove_from_cart()
		}
		else
		{
			Set<Map.Entry<ItemDetails, String>> enter= super.getcart().entrySet();
			
			for(Map.Entry<ItemDetails, String>entery:enter) 
			{
				price=entery.getKey().getitemprice();
				offer=(float)entery.getKey().getoffer();
				quantity=entery.getKey().getitemquntity();
				//System.out.println("Price: "+price+" offer: "+offer+" Quantity: "+quantity);
				qcounter=quantity+qcounter;
				float discount=price*(offer/100);
				temp=price-discount;
				temp=temp*quantity;
				//System.out.print("After product discount"+temp);
				
				end_amount+=temp;
				
			//	System.out.println();
				
				super.gethistory().add("Bought Item : "+ entery.getKey().getitemname()+" ,Quantity: "+quantity+" for Rs "+entery.getKey().getitemprice()+" from Restaurant "+rec.getname()+" and delivery charge: 20");
			}
		}
		
		temp=end_amount;
		
		if(rec.gettype().equals("FastFood"))
		{
			float sale=(float)rec.getdiscounts();
			temp=temp*(1-(sale/100));
		}
		else if(rec.gettype().equals("Authentic"))
		{
			float sale=(float)rec.getdiscounts();
			float dc=(sale/100)*temp;
			temp=temp-dc;
			if(temp>100)
			{
				temp=temp-50;
			}
		}
		
	//	System.out.print("After Restaurant discount: "+temp);
		if(temp>200)
			temp=temp-25;
		
	//	System.out.println("After Customer Discount: "+temp);
		
		end_amount=temp;
		
		if((end_amount+20)>(super.getreward()+super.getamount()) || flag==true)
		{
			System.out.println("Insufficient Balence, deleting all the elements of cart");
			super.gethistory().clear();
			super.getcart().clear();
			
			
		}
		else
		{
			super.getcart().clear();
			System.out.println("Delivery charge- 20/-");
			System.out.println("Total order value- "+(end_amount+20));
			System.out.println("1. Proceed with Payment");
			int val= in.nextInt();
			
			endall=(int)end_amount;
			if(val==1)
			{
				if(rec.gettype().equals("FastFood"))
				{
					reward_earned=(endall/150)*10;
				}
				else if(rec.gettype().equals("Authentic"))
				{
					reward_earned=(endall/200)*25;
				}
				else
					reward_earned=(endall/100)*5;
				
				
				final_amount=end_amount+20;
				
				end_amount=end_amount+20;
				
				if(super.getreward()>0)
				{
					if(super.getreward()>=end_amount)
					{
						super.setreward(super.getreward()-end_amount);
						end_amount=0;
						
					}
					else
					{
						end_amount=end_amount-super.getreward();
						super.setreward(0);
					}
					
				}
				if(end_amount>0)
				{
					if(super.getamount()>=end_amount)
					{
						super.setamount(super.getamount()-end_amount);
						end_amount=0;
						
					}
					else
					{
						end_amount=end_amount-super.getamount();
						super.setamount(0);
					}
				}
				if(end_amount>0)
					System.out.println("error in calculation");
				
				
				trancation_cost= final_amount*(0.01);
				
				super.setreward(super.getreward()+reward_earned);
				rec.addreword(reward_earned);
				
				rec.addorder(1);
				
				System.out.println(qcounter+ " item successfully bought for "+final_amount);
				
				
				
			}
		}
		return trancation_cost+" "+20;
	}
	
	
	
	
}
