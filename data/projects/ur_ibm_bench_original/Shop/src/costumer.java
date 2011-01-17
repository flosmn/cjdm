
import java.io.*;

class costumer extends Thread{
	
	Signal S;
	int id=0;
	int purchasedItems=0;
	shop my_shop;
	int shopping_list;
	DataOutputStream out;
	static Object lock=new Object(); 
	
	costumer(int amount,shop hanot,int id,DataOutputStream out,Signal S){
		shopping_list=amount;
		my_shop=hanot;	
		this.id=id;
		this.out=out;
        this.S=S;
		}
		
	/*
	 *	weak reality bug may occur
	 *	if two threads are trying
	 *	to buy- (see ReadMe file)
	 */
	 	
	void buy(){

		int i=shopping_list;
		while(i>0){
			if(!my_shop.isEmpty()){										
			try{	
				my_shop.getItem();
				purchasedItems++;
				i--;
			}
				catch(Exception e){
					System.out.println(e.toString());
					purchasedItems=-1;
					return;
					}
				
			}	
			/*
			 *if store is empty - gives up on that item.
			 */
			else{																									//if empty -go into else and after supplier added items.
				
				i--;
				}	
			}
		
	}
		
		
		
	public void run() {
		yield();
		System.out.println("Costumer "+id+" invoked");
		buy();
			
	   Integer I;
	   I=new Integer(id);
	   String idStr=I.toString();
	   I=new Integer(purchasedItems);
	   String purchasedItemsStr=I.toString();
	   String dataStr="   costumer  "+idStr+": "+purchasedItemsStr;
	   try{
	   my_shop.printCustomer(dataStr,out);		
	   }
	   catch(IOException e){System.out.println(e.toString());}
		int i=0;
		if(purchasedItems==-1)i=2;
		else if(purchasedItems<10)i=1;
		synchronized(lock)
		{
		   if(i!=0)if(S.get()!=2)S.set(i);
		}
		return;
     }
}