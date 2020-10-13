package Project_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Customer implements CustomerTypes{
	
	private String Name ;
	private String Adress;
	private String category;
	private float amount;
	private float reward;
	private HashMap<ItemDetails,String> cart;
	private ArrayList<String> history;
	
	Customer(String n, String a)
	{
		Name=n;
		Adress=a;
		amount=1000;
		reward=0;
		cart= new HashMap<>();
		history=new ArrayList<>();
		category="Normal";
	}
	
	public String getname() { return Name;}
	public String getAdress() { return Adress;}
	public String getcategory() { return category;}
	public float getamount() { return amount;}
	public float getreward() { return reward;}
	public HashMap<ItemDetails,String> getcart() { return cart;}
	public ArrayList<String> gethistory() {return history;}
	
	public void setname(String n) { Name=n;}
	public void setAdress(String a) { Adress=a; };
	public void setCategory(String s) { category=s;}
	public void setreward(float val) { reward=val;}
	public void setamount(float val) { amount=val;}
	
	public String transaction(HashMap<Integer,Restaurant> rrecords)
	{
		Scanner in= new Scanner (System.in);
		String name="";
		Boolean flag=false;
		int quantity=0;
		float price=0;
		float offer=0;
		Set<Map.Entry<ItemDetails, String>> enteries= cart.entrySet();
		
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
			Set<Map.Entry<ItemDetails, String>> enter= cart.entrySet();
			
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
				
				System.out.println();
				
				history.add("Bought Item : "+ entery.getKey().getitemname()+" ,Quantity: "+quantity+" for Rs "+entery.getKey().getitemprice()+" from Restaurant "+rec.getname()+" and delivery charge: 40");
				
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
		
		//System.out.print("After Restaurant discount: "+temp);
		
		end_amount=temp;
		
		if((end_amount+40)>(reward+amount) || flag==true )
		{
			System.out.println("Insufficient Balence, deleting all elements from cart");
			history.clear();
			cart.clear();
			
			
		}
		else
		{
			cart.clear();
			System.out.println("Delivery charge- 40/-");
			System.out.println("Total order value- "+(end_amount+40));
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
				
				
				final_amount=end_amount+40;
				
				end_amount=end_amount+40;
				
				if(reward>0)
				{
					if(reward>=end_amount)
					{
						reward=reward-end_amount;
						end_amount=0;
						
					}
					else
					{
						end_amount=end_amount-reward;
						reward=0;
					}
					
				}
				if(end_amount>0)
				{
					if(amount>=end_amount)
					{
						amount=amount-end_amount;
						end_amount=0;
						
					}
					else
					{
						end_amount=end_amount-amount;
						amount=0;
					}
				}
				if(end_amount>0)
					System.out.println("error in calculation");
				
				
				trancation_cost= final_amount*(0.01);
				
				reward=reward+reward_earned;
				rec.addreword(reward_earned);
				
				rec.addorder(1);
				
				
				System.out.println(qcounter+ " item successfully bought for "+final_amount);
				
				
				
			}
		}
		return trancation_cost+" "+40;
	}
	
	
	
	
	public void addCart(String rname, ItemDetails i,int id)
	{
		System.out.println("Enter item quantity -");
		Scanner in=new Scanner (System.in);
		
		int q=in.nextInt();
		
		ItemDetails order= new ItemDetails(i.getid());
		
		order.setitemname(i.getitemname());
		order.setitemprice(i.getitemprice());
		order.setitemquntity(q);
		order.setoffer(i.getoffer());
		order.setcategory(i.getcategory());
		
		String a=id+rname+" "+order.toString();
		
		cart.put(order, a);
		
		System.out.println("Items Added To The Cart");
		
	}
	
	public void PrintHistory ()
	{
		for(int i=0;i<history.size() && i<10 ;i++)
		{
			System.out.println(history.get(i));
		}
	}
	
	
	

}
