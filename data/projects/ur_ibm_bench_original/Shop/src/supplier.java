


class supplier extends Thread{
	
//data members	
	
	int itemsToBeSupplied=0; 
	shop supply_address;	
	
		/*
		 *constructor - items to be suppleied= 10 * #costumers
		 */
	supplier(shop address,int costumers_amount){	
		supply_address=address;			
		itemsToBeSupplied=10*costumers_amount;
	}   
		
	public void run(){		

		yield();
		System.out.println("started supplier");
		supply();
		System.out.println("finished supplier");
		}
	
	void supply(){
		int i=itemsToBeSupplied;		
		while(i>0){			
			supply_address.putItem();
			i--;
			}
		}
}