package Project_2;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerTypes {
	
	public String getname() ;
	public String getAdress();
	public String getcategory();
	public float getamount() ;
	public float getreward() ;
	public void setreward(float val);
	public void setamount(float val);
	public HashMap<ItemDetails,String> getcart() ;
	public ArrayList<String> gethistory() ;
	public void setname(String n);
	public void setAdress(String a);
	public void addCart(String rname, ItemDetails i,int td);
	public void PrintHistory ();
	public String transaction(HashMap<Integer,Restaurant> rrecords);
	

}
