package Project_2;

import java.util.*;

public class Restaurant {
	
	static int counter=0;
	private String name;
	private String adress;
	protected String type;
	private float reword;
	private int discount;
	private int ordercount;
	private HashMap<Integer,ItemDetails> foodoptions;
	
	Restaurant(String n, String a,String t)
	{
		name=n;
		adress=a;
		type=t;
		reword=0;
		discount=0;
		ordercount=0;
		foodoptions=new HashMap<>();
	}
	
	public void setname(String n) { name=n;}
	public void setadress(String a) { adress=a;}
	public void setdiscount(int d) {discount=d;}
	public void addreword(float v) { reword=reword+v;}
	public void addorder(int n) { ordercount=ordercount+1;}
	
	public String getname() { return name;}
	public String getadress() { return adress;}
	public String gettype() { return type;}
	public float getreward()  {return reword;}
	public int getdiscounts() { return discount;}
	public int getordercount() { return ordercount;}
	public HashMap<Integer,ItemDetails> getfoodoptions(){return foodoptions;}
	
	public void add_item()
	{
		Scanner in=new Scanner (System.in);
		counter=counter+1;
		ItemDetails ii= new ItemDetails(counter);
		System.out.println("Enter Food Item Details");
		System.out.println("Food name");
		ii.setitemname(in.nextLine());
		System.out.println("Item Price");
		ii.setitemprice(in.nextFloat());
		System.out.println("Item quantity");
		ii.setitemquntity(in.nextInt());
		System.out.println("Item Category");
		in.nextLine();
		ii.setcategory(in.nextLine());
		System.out.println("Offer:");
		ii.setoffer(in.nextInt());
		
		
		foodoptions.put(ii.getid(),ii);
		System.out.println(ii.toString());
		
	}
	
	public void edit_item(String n)
	{
		Scanner in=new Scanner (System.in);
		System.out.println("Choose Item by code");
		
		Set<Map.Entry<Integer, ItemDetails>> enter= foodoptions.entrySet();
		
		for(Map.Entry<Integer, ItemDetails>entery:enter) 
		{
			System.out.print(n+" ");
			System.out.println(entery.getValue().toString());
		}
		
		
//		for(int i=0;i<foodoptions.size();i++)
//		{
//			System.out.print(n+" ");
//			System.out.println(foodoptions.get(i+1).toString());
//		}
		
		int option=in.nextInt();
		
		ItemDetails edit=foodoptions.get(option);
		
		System.out.println("Choose an attribite to edit");
		System.out.println("1. Name");
		System.out.println("2. Price");
		System.out.println("3. Quantity");
		System.out.println("4. Category");
		System.out.println("5. Offer");
		option=in.nextInt();
		if(option==1)
		{
			System.out.println("Enter the new Name");
			edit.setitemname(in.next());
		}
		else if(option==2)
		{
			System.out.println("Enter the new price");
			edit.setitemprice(in.nextFloat());
		}
		else if(option==3)
		{
			System.out.println("Enter the new quantity");
			edit.setitemquntity(in.nextInt());
		}
		else if(option ==4)
		{
			System.out.println("Enter the new Category");
			edit.setcategory(in.next());
		}
		else if(option==5)
		{
			System.out.println("Enter the new Offer");
			edit.setoffer(in.nextInt());
			
		}
		else
		{
			System.out.println("Invalid option");
		}
		
		System.out.println(edit.toString());
	}
	
}
