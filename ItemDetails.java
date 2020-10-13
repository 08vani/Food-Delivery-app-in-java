package Project_2;

public class ItemDetails {
	
	private String itemname;
	private float itemprice;
	private int itemquantity;
	private String category;
	private int offer;
	private  final int id;
	
	ItemDetails(int i)
	{
		id=i;
	}
	
	public void setitemname(String n) { itemname=n;}
	public void setitemprice(float p) { itemprice=p;}
	public void setitemquntity(int q) { itemquantity=q;}
	public void setcategory(String c) {category=c;}
	public void setoffer(int o) {offer=o;}
	
	
	public String getitemname() { return itemname;}
	public float getitemprice() { return itemprice;}
	public int getitemquntity() { return itemquantity;}
	public String getcategory() {return category;}
	public int getoffer() {return offer;}
	public int getid() { return id;}
	
	public String toString()
	{
		return id+" "+itemname+" "+itemprice+" "+itemquantity+" "+offer+"% off "+category;
	}
	
	
	

}
